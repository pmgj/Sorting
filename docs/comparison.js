/* global algorithms */

const getShuffledArr = arr => {
    const newArr = arr.slice();
    for (let i = newArr.length - 1; i > 0; i--) {
        const rand = Math.floor(Math.random() * (i + 1));
        [newArr[i], newArr[rand]] = [newArr[rand], newArr[i]];
    }
    return newArr;
};
function printTable(data) {
    let getSortedKeys = (obj) => {
        var keys = Object.keys(obj);
        return keys.sort((a, b) => obj[a] - obj[b]);
    };
    let sorted = getSortedKeys(data);
    let str = "<table><thead><tr><th>Method</th><th>Elapsed Time (ms)</th></tr></thead><tbody>";
    for (let key in sorted) {
        str += `<tr><td>${sorted[key]}</td><td>${data[sorted[key]].toFixed(2)}</td></tr>`;
    }
    str += "</tbody></table>";
    document.querySelector("#output").innerHTML = str;
}
function testAllAlgorithmsSequential(shuffledArrays, algoIndexes) {
    let ret = [];
    for (let alg of algoIndexes) {
        let elapsed = 0;
        for (let arr of shuffledArrays) {
            let t0 = performance.now();
            algorithms[alg].method(arr);
            let t1 = performance.now();
            elapsed += t1 - t0;
        }
        ret[algorithms[alg].name] = elapsed;
    }
    printTable(ret);
}
function testAllAlgorithmsParallel(shuffledArrays, algoIndexes) {
    let ret = [];
    for (let j of algoIndexes) {
        let sortWorker = new Worker('SortWorker.js');
        sortWorker.onmessage = (e) => {
            let obj = e.data;
            ret[algorithms[obj.method].name] = obj.elapsed;
            if (Object.keys(ret).length === algorithms.length) {
                printTable(ret);
            }
        };
        sortWorker.postMessage({index: j, array: shuffledArrays});
    }
}
function run() {
    let form = document.forms[0];
    const TAM_ARRAY = parseInt(form.qtd.value);
    const NUM_REP = parseInt(form.rep.value);
    const TYPE = parseInt(form.type.value);

    let shuffledArrays = [];
    let orig = [...Array(TAM_ARRAY)].map((_, i) => i + 1);
    for (let i = 0; i < NUM_REP; i++) {
        shuffledArrays.push(getShuffledArr(orig));
    }

    document.querySelector("#output").innerHTML = "Running";

    let selectedAlgorithms = document.querySelectorAll("input[type='checkbox']:checked");
    let mappedValues = Array.prototype.map.call(selectedAlgorithms, (item) => item.value);
    let types = [testAllAlgorithmsParallel, testAllAlgorithmsSequential];
    types[TYPE](shuffledArrays, mappedValues);
}
window.onload = function () {
    let form = document.forms[0];
    form.run.onclick = run;

    let div = document.querySelector("form div");
    for (let i = 0; i < algorithms.length; i++) {
        div.innerHTML += `<input type="checkbox" checked="checked" name="sortingAlgorithm" value="${i}" /><span>${algorithms[i].name}</span><br />`;
    }
};
