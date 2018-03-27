var suite = new Benchmark.Suite;
// ************ BEGIN: Array Tests prepare *************** //
var arrayTests = {
    ret: [],
    tmp: 0,
    num: 500,
    i: 1024
};
// ************ END: Array Tests prepare *************** //

// ************ BEGIN: Eval Tests prepare *************** //
var evalTests = {
    ret: '',
    tmp: '',
    cmd: 'var str="";for(var i=0;i<1000;i++){str += "a";}ret = str;',
    num: 4
};
// ************ END: Eval Tests prepare *************** //

// ************ BEGIN: Base64 Tests prepare *************** //

var base64Tests = {
    toBase64Table: 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/',
    base64Pad: '=' /* Convert data (an array of integers) to a Base64 string. */,
    toBinaryTable: [
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, /* Convert Base64 data to a string */
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
        52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, 0, -1, -1,
        -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
        -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1
    ],
    strForBase64: [],
    base64: '',
    toBase64: function (data) {
        var result = '';
        var length = data.length;
        var i;
        // Convert every three bytes to 4 ascii characters.
        for (i = 0; i < (length - 2); i += 3) {
            result += base64Tests.toBase64Table[data.charCodeAt(i) >> 2];
            result += base64Tests.toBase64Table[((data.charCodeAt(i) & 0x03) << 4) + (data.charCodeAt(i + 1) >> 4)];
            result += base64Tests.toBase64Table[((data.charCodeAt(i + 1) & 0x0f) << 2) + (data.charCodeAt(i + 2) >> 6)];
            result += base64Tests.toBase64Table[data.charCodeAt(i + 2) & 0x3f];
        }
        // Convert the remaining 1 or 2 bytes, pad out to 4 characters.
        if (length % 3) {
            i = length - (length % 3);
            result += base64Tests.toBase64Table[data.charCodeAt(i) >> 2];
            if ((length % 3) == 2) {
                result += base64Tests.toBase64Table[((data.charCodeAt(i) & 0x03) << 4) + (data.chartCodeAt(i + 1) >> 4)];
                result += base64Tests.toBase64Table[(data.charCodeAt(i + 1) & 0x0f) << 2];
                result += base64Tests.base64Pad;
            } else {
                result +=base64Tests.toBase64Table[(data.charCodeAt(i) & 0x03) << 4];
                result += base64Tests.base64Pad + base64Tests.base64Pad;
            }
        }

        return result;
    },
    base64ToString: function (data) {
        var result = '';
        var leftbits = 0; // number of bits decoded, but yet to be appended
        var leftdata = 0; // bits decoded, but yet to be appended

        // Convert one by one.
        for (var i = 0; i < data.length; i++) {
            var c = base64Tests.toBinaryTable[data.charCodeAt(i) & 0x7f];
            var padding = (data.charCodeAt(i) == base64Tests.base64Pad.charCodeAt(0));
            // Skip illegal characters and whitespace
            if (c == -1) continue;

            // Collect data into leftdata, update bitcount
            leftdata = (leftdata << 6) | c;
            leftbits += 6;

            // If we have 8 or more bits, append 8 bits to the result
            if (leftbits >= 8) {
                leftbits -= 8;
                // Append if not padding.
                if (!padding)
                    result += String.fromCharCode((leftdata >> leftbits) & 0xff);
                leftdata &= (1 << leftbits) - 1;
            }
        }

        // If there are any bits left, the base64 string was corrupted
        if (leftbits)
            throw 'Corrupted base64 string';

        return result;
    }
};

function prepareBase64TestsData() {
    for (var i = 0; i < 4096; i++)
        base64Tests.strForBase64.push(String.fromCharCode((25 * Math.random()) + 97));

    base64Tests.strForBase64 = base64Tests.strForBase64.join("");
    base64Tests.strForBase64 += base64Tests.strForBase64;
    base64Tests.strForBase64 += base64Tests.strForBase64;
}
prepareBase64TestsData();
// ************ END: Base64 Tests prepare *************** //

// ************ BEGIN: RegExp Tests prepare *************** //
var regexpTests = {
    str: [],
    tmp: '',
    ret: '',
    re: {},
    testStrings: [],
    randomChar: function () {
        return String.fromCharCode((25 * Math.random()) + 97);
    },
    generateTestStrings: function (count) {
        var t, nest;
        if (regexpTests.testStrings.length >= count)
            return regexpTests.testStrings.slice(0, count);
        for (var i = regexpTests.testStrings.length; i < count; i++) {
            // Make all tested strings different
            t =  regexpTests.randomChar() + regexpTests.str + regexpTests.randomChar();
            nest = Math.floor(4 * Math.random());
            for (var j = 0; j < nest; j++) {
                t = regexpTests.randomChar() + t + regexpTests.randomChar();
            }
            // Try to minimize benchmark order dependencies by
            // exercising the strings
            for (var j = 0; j < t.length; j += 100) {
                regexpTests.ret = t[j];
                regexpTests.ret = t.substring(j, j + 100);
            }
            regexpTests.testStrings[i] = t;
        }
        return regexpTests.testStrings;
    }
};

function prepareRegexpTestData() {
    for (regexpTests.i = 0; regexpTests.i < 16384; regexpTests.i++)
        regexpTests.str.push(regexpTests.randomChar());

    regexpTests.str = regexpTests.str.join("");
    regexpTests.str += regexpTests.str;
    regexpTests.str += regexpTests.str;
}

prepareRegexpTestData();
// ************ END: RegExp Tests prepare

var results = [];
// ************ TESTS *************** //
suite.add('perf#sunspider-3d-morph', function () {
    var loops = 15, nx, nz, a;
    var size = 120;
    nz = nx = size / 3;

    function morph(a, f) {
        var PI2nx = Math.PI * 8 / nx;
        var sin = Math.sin;
        var f30 = -(50 * sin(f * Math.PI * 2));

        for (var i = 0; i < nz; ++i) {
            for (var j = 0; j < nx; ++j) {
                a[3 * (i * nx + j) + 1] = sin((j - 1) * PI2nx) * -f30
            }
        }
    }

    a = Array();
    for (var i = 0; i < nx * nz * 3; ++i)
        a[i] = 0;

    for (var i = 0; i < loops; ++i) {
        morph(a, i / loops)
    }
})
    .add('Array Construction, []', function () {
        for (var j = 0; j < arrayTests.i * 15; j++) {
            arrayTests.ret = [];
            arrayTests.ret.length = arrayTests.i;
        }
    })
    .add('Array Construction, new Array()', function () {
        for (var j = 0; j < arrayTests.i * 10; j++)
            arrayTests.ret = new Array(arrayTests.i);
    })
    .add('Array Construction, unshift', function () {
        arrayTests.ret = [];
        for (var j = 0; j < arrayTests.i; j++)
            arrayTests.ret.unshift(j);
    })
    .add('Array Construction, splice', function () {
        arrayTests.ret = [];
        for (var j = 0; j < arrayTests.i; j++)
            arrayTests.ret.splice(0, 0, j);
    })
    .add('Array Deconstruction, shift', function () {
        var a = arrayTests.ret.slice();
        for (var j = 0; j < arrayTests.i; j++)
            arrayTests.tmp = a.shift();
    })
    .add("Array Deconstruction, splice", function () {
        var a = arrayTests.ret.slice();
        for (var j = 0; j < arrayTests.i; j++)
            arrayTests.tmp = a.splice(0, 1);
    })
    .add("Array Construction, push", function () {
        arrayTests.ret = [];
        for (var j = 0; j < arrayTests.i * 25; j++)
            arrayTests.ret.push(j);
    })
    .add("Array Deconstruction, pop", function () {
        var a = arrayTests.ret.slice();
        for (var j = 0; j < arrayTests.i * 25; j++)
            arrayTests.tmp = a.pop();
    })
    .add("Convert String to Base 64", function () {
        base64Tests.base64 = base64Tests.toBase64(base64Tests.strForBase64);
    })
    .add("Convert Base 64 to String", function () {
        if (base64Tests.strForBase64 !== base64Tests.base64ToString(base64Tests.base64)) {
            throw "String conversion mis-match.";
        }
    }, {
        setup: function () {
            if (!base64Tests.base64)
                base64Tests.base64 = base64Tests.toBase64(base64Tests.strForBase64);
        }
    })
    .add("Normal eval", function () {
        eval(evalTests.tmp);
    }, {
        setup: function () {
            evalTests.tmp = evalTests.cmd;
            for (var n = 0; n < evalTests.num; n++)
                evalTests.tmp += evalTests.tmp;
        }
    })
    .add("new Function", function () {
        (new Function(evalTests.tmp))();
    }, {
        setup: function () {
            evalTests.tmp = evalTests.cmd;
            for (var n = 0; n < evalTests.num; n++)
                evalTests.tmp += evalTests.tmp;
        }
    })
    .add("Compiled Object Char Split", function () {
        for (var i = 0; i < 5; i++)
            regexpTests.ret = regexpTests.tmp[i].split(regexpTests.re);
    }, {
        setup: function () {
            regexpTests.re = /a/;
            regexpTests.tmp = regexpTests.generateTestStrings(5);
        }
    })
    .add("Compiled Object Variable Split", function () {
        for (var i = 0; i < 5; i++)
            regexpTests.ret = regexpTests.tmp[i].split(regexpTests.re);
    }, {
        setup: function () {
            regexpTests.re = /.*/;
            regexpTests.tmp = regexpTests.generateTestStrings(5);
        }
    })
    .add("Compiled Match", function () {
        for (var i = 0; i < 5; i++)
            regexpTests.ret = regexpTests.tmp[i].match(regexpTests.re);
    }, {
        setup: function () {
            regexpTests.re = /aaaaaaaaaa/g;
            regexpTests.tmp = regexpTests.generateTestStrings(5);
        }
    })
    .add("Compiled Test", function () {
        for (var i = 0; i < 5; i++)
            regexpTests.ret = regexpTests.re.test(regexpTests.tmp[i]);
    }, {
        setup: function () {
            regexpTests.tmp = regexpTests.generateTestStrings(5);
        }
    })
    .add("Compiled Empty Replace", function () {
        for (var i = 0; i < 5; i++)
            regexpTests.ret = regexpTests.tmp[i].replace(regexpTests.re, "");
    }, {
        setup: function () {
            regexpTests.tmp = regexpTests.generateTestStrings(5);
        }
    })
    // add listeners
    .on('cycle', function (event) {
        var current = event.target;
        var value = {name: current.name, stats: current.stats, speed: current.hz};
        results.push(value);
        print(String(event.target));
    })
    .on('complete', function () {
        writeToFile('warmup/nashorn/results-iter'+iterNo+'.out', JSON.stringify(results));
        print('Fastest is ' + this.filter('fastest').map('name'));
    })
    // .on('error', function (e) {
    //     print('Error: ' + JSON.stringify(e));
    // })
    .run({'async': true});