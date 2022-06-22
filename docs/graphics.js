export class UIGraphics {
    constructor(name) {
        this.name = name;
        this.outerSpace = 5;
        this.innerSpace = 1;
    }
    setGraphics(g, width, height, qtd) {
        this.ctx = g;
        this.WIDTH = width;
        this.HEIGHT = height;
        this.QTD = qtd;
        this.update();
    }
    toString() {
        return this.name;
    }
}
class Bars extends UIGraphics {
    constructor() {
        super("Bars");
    }
    draw(idx, n) {
        this.ctx.fillStyle = `hsla(${(this.colorDiff * n)}, 100%, 50%, 1.0)`;
        this.ctx.fillRect(idx * (this.width + this.innerSpace) + this.outerSpace, this.outerSpace, this.width, this.height);
    }
    update() {
        this.width = (this.WIDTH - 2 * this.outerSpace - (this.QTD - 1) * this.innerSpace) / this.QTD;
        this.height = this.HEIGHT - 2 * this.outerSpace;
        this.colorDiff = 360 / this.QTD;
    }
}
class Circle extends UIGraphics {
    constructor() {
        super("Circle");
    }
    draw(idx, n) {
        this.ctx.beginPath();
        this.ctx.moveTo(this.cx, this.cy);
        this.ctx.arc(this.cx, this.cy, this.radius, idx * this.baseAngle, (idx + 1) * this.baseAngle);
        let color = `hsla(${(this.colorDiff * n)}, 100%, 50%, 1.0)`;
        this.ctx.fillStyle = color;
        this.ctx.fill();
        this.ctx.strokeStyle = color;
        this.ctx.stroke();
        this.ctx.closePath();
    }
    update() {
        this.cx = this.WIDTH / 2;
        this.cy = this.HEIGHT / 2;
        this.radius = this.cy - 2 * this.outerSpace;
        this.colorDiff = 360 / this.QTD;
        this.baseAngle = (2 * Math.PI) / this.QTD;
    }
}
class DisparityDots extends UIGraphics {
    constructor() {
        super("DisparityDots");
    }
    draw(idx, n) {
        this.ctx.beginPath();
        let x = this.cx + (this.WIDTH - 2 * this.radius - 2 * this.outerSpace) / 2 * (Math.cos(idx * 2 * Math.PI / this.QTD)) * (1 - (Math.abs(n - idx - 1) / this.QTD));
        let y = this.cy + (this.HEIGHT - 2 * this.radius  - 2 * this.outerSpace) / 2 * (Math.sin(idx * 2 * Math.PI / this.QTD)) * (1 - (Math.abs(n - idx - 1) / this.QTD));
        this.ctx.arc(x, y, this.radius, 0, 2 * Math.PI);
        let color = `hsla(${(this.colorDiff * n)}, 100%, 50%, 1.0)`;
        this.ctx.fillStyle = color;
        this.ctx.fill();
        this.ctx.strokeStyle = color;
        this.ctx.stroke();
        this.ctx.closePath();
    }
    update() {
        this.cx = this.WIDTH / 2;
        this.cy = this.HEIGHT / 2;
        this.radius = Math.min(this.cx, this.cy) / this.QTD;
        this.colorDiff = 360 / this.QTD;
    }
}
class Histogram extends UIGraphics {
    constructor() {
        super("Histogram");
    }
    draw(idx, n) {
        let color = ["violet", "indigo", "blue", "green", "yellow", "orange", "red"];
        this.ctx.fillStyle = color[n % 7];
        this.ctx.fillRect(idx * (this.width + this.innerSpace) + this.outerSpace, this.HEIGHT - n * this.height - this.outerSpace, this.width, n * this.height);
    }
    update() {
        this.width = (this.WIDTH - 2 * this.outerSpace - (this.QTD - 1) * this.innerSpace) / this.QTD;
        this.height = (this.HEIGHT - 2 * this.outerSpace) / this.QTD;
    }
}
class InnerCircle extends UIGraphics {
    constructor() {
        super("InnerCircle");
    }
    draw(idx, n) {
        this.ctx.beginPath();
        this.ctx.arc(this.cx, this.cy, (idx * this.lineWidth) + this.lineWidth / 2, 0, 2 * Math.PI);
        this.ctx.lineWidth = this.lineWidth;
        this.ctx.strokeStyle = `hsla(${(this.colorDiff * n)}, 100%, 50%, 1.0)`;
        this.ctx.stroke();
        this.ctx.closePath();
    }
    update() {
        this.cx = this.WIDTH / 2;
        this.cy = this.HEIGHT / 2;
        this.lineWidth = (this.cy - 2 * this.outerSpace) / this.QTD;
        this.colorDiff = 360 / this.QTD;
    }
}
class Line extends UIGraphics {
    constructor() {
        super("Line");
    }
    draw(idx, n) {
        let color = ["violet", "indigo", "blue", "green", "yellow", "orange", "red"];
        this.ctx.fillStyle = color[n % 7];
        this.ctx.fillRect(idx * (this.width + this.innerSpace) + this.outerSpace, this.HEIGHT - n * this.height - this.outerSpace, this.width, this.height);
    }
    update() {
        this.width = (this.WIDTH - 2 * this.outerSpace - (this.QTD - 1) * this.innerSpace) / this.QTD;
        this.height = (this.HEIGHT - 2 * this.outerSpace) / this.QTD;
    }
}
class Pyramid extends UIGraphics {
    constructor() {
        super("Pyramid");
    }
    draw(idx, n) {
        let color = ["violet", "indigo", "blue", "green", "yellow", "orange", "red"];
        this.ctx.fillStyle = color[n % 7];
        this.ctx.fillRect(this.WIDTH / 2 - n * this.width / 2, idx * (this.height + this.innerSpace) + this.outerSpace, n * this.width, this.height);
    }
    update() {
        this.width = (this.WIDTH - 2 * this.outerSpace) / this.QTD;
        this.height = (this.HEIGHT - 2 * this.outerSpace - (this.QTD - 1) * this.innerSpace) / this.QTD;
    }
}
class Swirl extends UIGraphics {
    constructor() {
        super("Swirl");
    }
    draw(idx, n) {
        let ratio = n / this.QTD;
        let angle = idx * this.angle_incr;
        let spiral_rad = ratio * this.outer_rad;
        let x = this.cx + Math.cos(angle) * spiral_rad;
        let y = this.cy + Math.sin(angle) * spiral_rad;
        this.ctx.beginPath();
        this.ctx.arc(x, y, this.sm_rad, 0, 2 * Math.PI, false);
        let color = ["violet", "indigo", "blue", "green", "yellow", "orange", "red"];
        this.ctx.fillStyle = color[n % 7];
        this.ctx.fill();
    }
    update() {
        this.cx = this.WIDTH / 2;
        this.cy = this.HEIGHT / 2;
        this.outer_rad = this.WIDTH * .15;
        this.sm_rad = 2;
        this.angle_incr = 6 * Math.PI / 180;
    }
}
export const graphics = [new Bars(), new Circle(), new DisparityDots(), new Histogram(), new InnerCircle(), new Line(), new Pyramid(), new Swirl()];
