/**
 * css/script资源压缩配置
 * 使用前需要安装
 * 1. node&npm
 * 2. npm install --save-dev gulp gulp-concat gulp-minify-css gulp-uglify gulp-strip-debug gulp-react
 *
 * gulp 教程:http://www.smashingmagazine.com/2014/06/11/building-with-gulp/
 */
var gulp = require('gulp');

var react = require('gulp-react');
gulp.task('react', function () {
    return gulp.src('*.jsx')
        .pipe(react())
        .pipe(gulp.dest('./'));
});

gulp.task("watch", function () {
    gulp.watch("*.jsx", ["react"]);
});

