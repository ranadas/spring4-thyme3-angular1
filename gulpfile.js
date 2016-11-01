var gulp = require('gulp');
var args = require('yargs').argv; // for command line arguments. e.g. .pipe(gulpif(args.verbose, gulpprint()))

//var jshint = require('gulp-jshint');
//var jscs = require('gulp-jscs');
//var util = require('gulp-util'); // for using the util.log
//var gulpprint = require('gulp-print'); // for printing all src files being accessed.
//var gulpif = require('gulp-if'); // for using gulp if. e.g .pipe(gulpif(args.verbose, gulpprint()))

// all the above gulp imports, replaced by
var $$ = require('gulp-load-plugins')({lazy:true});

//Ignore all .min files
gulp.task('vet', function () {
    log('\n--> Vetting, Analyzing js files with jscs and Jshint.\n');

    return gulp
        .src(['./src/**/*.js',
            './*.js',
            '!./src/**/*.min.js'])
        .pipe($$.if(args.verbose, $$.print()))
        .pipe($$.jscs())
        .pipe($$.jshint())
        .pipe($$.jshint.reporter('jshint-stylish', {verbose: true}))
        .pipe($$.jshint.reporter(('fail')));

    // withour loader plugins
    //return gulp
    //    .src(['./src/**/*.js',
    //        './*.js',
    //        '!./src/**/*.min.js'])
    //    .pipe(gulpif(args.verbose, gulpprint()))
    //    .pipe(jscs())
    //    .pipe(jshint())
    //    .pipe(jshint.reporter('jshint-stylish', {verbose: true}))
    //    .pipe(jshint.reporter(('fail')));
});


gulp.task('hello-world', function () {
    console.log('Hello Rana');
});

function log(message) {

    if (typeof (message) === 'object') {
        for (var item in message) {
            if (message.hasOwnProperty(item)) {
                $$.util.log($$.util.colors.blue(message[item]));
            }
        }
    } else {
        $$.util.log($$.util.colors.blue(message));
    }
}

