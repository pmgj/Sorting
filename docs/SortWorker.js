importScripts('sorting.js');
onmessage = function (e) {
    let obj = e.data, alg = algorithms[obj.index].method, elapsedTime = 0;
    for (let arr of obj.array) {
        let t0 = performance.now();
        alg(arr);
        let t1 = performance.now();
        elapsedTime += t1 - t0;
    }
    postMessage({method: obj.index, elapsed: elapsedTime / obj.array.length});
};
