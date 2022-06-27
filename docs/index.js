import {SortingAlgorithm, algorithms} from "./sorting.js";
import {UIGraphics, graphics} from "./graphics.js";

class MyPanel {
    constructor() {
        this.cbGraphics = null;
        this.cbAlgorithms = null;
        this.sQuantity = null;
        this.sDelay = null;
        this.bShuffle = null;
        this.bRun = null;
        this.commands = null;
    }
    shuffle() {
        let qtd = parseInt(this.sQuantity.value);
        let v = [...Array(qtd)].map((_, i) => i + 1);
        this.numbers = this.getShuffledArr(v);
        this.print();
    }
    print() {
        let c = document.getElementById('gc');
        let ctx = c.getContext('2d');
        this.graphic = graphics[this.cbGraphics.value];
        this.graphic.setGraphics(ctx, c.width, c.height, this.numbers.length);
        ctx.clearRect(0, 0, c.width, c.height);
        ctx.fillStyle = 'black';
        ctx.fillRect(0, 0, c.width, c.height);
        let first = this.commands.shift();
        if (first) {
            let {action, index, val} = first;
            for (let [idx, n] of this.numbers.entries()) {
                this.graphic.draw(idx, n);
            }
            if (action === "write") {
                this.numbers[index] = val;
            }
            let delay = parseInt(this.sDelay.value);
            setTimeout(this.print.bind(this), delay);
        } else {
            for (let [idx, n] of this.numbers.entries()) {
                this.graphic.draw(idx, n);
            }
            this.bShuffle.disabled = false;
            this.bRun.disabled = false;
        }
    }
    getShuffledArr(arr) {
        const newArr = arr.slice();
        for (let i = newArr.length - 1; i > 0; i--) {
            const rand = Math.floor(Math.random() * (i + 1));
            [newArr[i], newArr[rand]] = [newArr[rand], newArr[i]];
        }
        return newArr;
    }
    run() {
        const _that = this;
        const read = {
            get: function (target, property) {
                if (!isNaN(property)) {
                    _that.commands.push({action: "read", index: property, val: target[property]});
                }
                return target[property];
            },
            set: function (target, property, value) {
                target[property] = value;
                _that.commands.push({action: "write", index: property, val: value});
                return true;
            }
        };
        let method = this.cbAlgorithms.value;
        let temp = this.numbers.slice();
        let proxy = new Proxy(temp, read);
        algorithms[method].sort(proxy);
        this.bShuffle.disabled = true;
        this.bRun.disabled = true;
        this.print();
    }
    registerEvents() {
        let form = document.forms[0];
        let select = form.algorithm;
        for (let i in algorithms) {
            let option = document.createElement("option");
            option.text = algorithms[i];
            option.value = i;
            select.add(option);
        }
        select = form.graphic;
        for (let i in graphics) {
            let option = document.createElement("option");
            option.text = graphics[i];
            option.value = i;
            select.add(option);
        }
        this.cbGraphics = form.graphic;
        this.cbAlgorithms = form.algorithm;
        this.sQuantity = form.qtd;
        this.sDelay = form.delay;
        this.bShuffle = form.shuffle;
        this.bRun = form.run;
        this.commands = [];
        form.run.onclick = this.run.bind(this);
        form.shuffle.onclick = this.shuffle.bind(this);
        form.graphic.onchange = this.print.bind(this);
        this.shuffle();
    }
}
let panel = new MyPanel();
panel.registerEvents();
