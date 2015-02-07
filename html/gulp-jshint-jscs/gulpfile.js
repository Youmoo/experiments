var gulp = require('gulp');
var notify = require('gulp-notify');
var jscs = require('gulp-jscs');
var jshint = require('gulp-jshint');

// For windows users setup a growl notifier
//var growlNotifier = growl();

gulp.task('jscs', function () {
    gulp.src('js/*.js')
        .pipe(jscs())
        .pipe(notify({
            title: 'JSCS',
            message: 'JSCS Passed. Let it fly!'
        }))
});

gulp.task('lint', function () {
    return gulp.src('app.js')
        .pipe(jshint())
        .pipe(jshint.reporter('default'));
});