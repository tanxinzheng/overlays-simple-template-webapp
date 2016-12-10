// lazyload config
define(function() {
    return angular.module('config.lazyload',[])
    /**
     * jQuery plugin config use ui-jq directive , config the js and css files that required
     * key: function name of the jQuery plugin
     * value: array of the css js file located
     */
        .constant('JQ_CONFIG', {
            easyPieChart: ['js/vendor/jquery/charts/easypiechart/jquery.easy-pie-chart.js'],
            sparkline: ['js/vendor/jquery/charts/sparkline/jquery.sparkline.min.js'],
            plot: ['js/vendor/jquery/charts/flot/jquery.flot.min.js',
                'js/vendor/jquery/charts/flot/jquery.flot.resize.js',
                'js/vendor/jquery/charts/flot/jquery.flot.tooltip.min.js',
                'js/vendor/jquery/charts/flot/jquery.flot.spline.js',
                'js/vendor/jquery/charts/flot/jquery.flot.orderBars.js',
                'js/vendor/jquery/charts/flot/jquery.flot.pie.min.js'],
            slimScroll: ['js/vendor/jquery/slimscroll/jquery.slimscroll.min.js'],
            sortable: ['js/vendor/jquery/sortable/jquery.sortable.js'],
            nestable: ['js/vendor/jquery/nestable/jquery.nestable.js',
                'js/vendor/jquery/nestable/nestable.css'],
            filestyle: ['js/vendor/jquery/file/bootstrap-filestyle.min.js'],
            slider: ['js/vendor/jquery/slider/bootstrap-slider.js',
                'js/vendor/jquery/slider/slider.css'],
            chosen: ['js/vendor/jquery/chosen/chosen.jquery.min.js',
                'js/vendor/jquery/chosen/chosen.css'],
            TouchSpin: ['js/vendor/jquery/spinner/jquery.bootstrap-touchspin.min.js',
                'js/vendor/jquery/spinner/jquery.bootstrap-touchspin.css'],
            wysiwyg: ['js/vendor/jquery/wysiwyg/bootstrap-wysiwyg.js',
                'js/vendor/jquery/wysiwyg/jquery.hotkeys.js'],
            dataTable: ['js/vendor/jquery/datatables/jquery.dataTables.min.js',
                'js/vendor/jquery/datatables/dataTables.bootstrap.js',
                'js/vendor/jquery/datatables/dataTables.bootstrap.css'],
            vectorMap: ['js/vendor/jquery/jvectormap/jquery-jvectormap.min.js',
                'js/vendor/jquery/jvectormap/jquery-jvectormap-world-mill-en.js',
                'js/vendor/jquery/jvectormap/jquery-jvectormap-us-aea-en.js',
                'js/vendor/jquery/jvectormap/jquery-jvectormap.css'],
            footable: ['js/vendor/jquery/footable/footable.all.min.js',
                'js/vendor/jquery/footable/footable.core.css']
        }
    )
        // oclazyload config
        .config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
            // We configure ocLazyLoad to use the lib script.js as the async loader
            $ocLazyLoadProvider.config({
                debug: false,
                events: true,
                modules: [
                    {
                        name: 'ngGrid',
                        files: [
                            'js/vendor/modules/ng-grid/ng-grid.min.js',
                            'js/vendor/modules/ng-grid/ng-grid.min.css',
                            'js/vendor/modules/ng-grid/theme.css'
                        ]
                    },
                    {
                        name: 'ui.select',
                        files: [
                            'bower_components/angular-ui-select/dist/select.min.js',
                            'bower_components/angular-ui-select/dist/select.min.css'
                        ]
                    },
                    {
                        name: 'angularFileUpload',
                        files: [
                            'js/vendor/angular/angular-file-upload/angular-file-upload.min.js'
                        ]
                    },
                    {
                        name: 'ngFileUpload',
                        files: [
                            'bower_components/ng-file-upload/ng-file-upload.js'
                        ]
                    },
                    {
                        name: 'ui.calendar',
                        files: ['js/vendor/angular/angular-ui-calendar/calendar.js']
                    },
                    {
                        name: 'ngImgCrop',
                        files: [
                            'js/vendor/modules/ngImgCrop/ng-img-crop.js',
                            'js/vendor/modules/ngImgCrop/ng-img-crop.css'
                        ]
                    },
                    {
                        name: 'angularBootstrapNavTree',
                        files: [
                            'js/vendor/angular/angular-bootstrap-nav-tree/abn_tree_directive.js',
                            'js/vendor/angular/angular-bootstrap-nav-tree/abn_tree.css'
                        ]
                    },
                    {
                        name: 'toaster',
                        files: [
                            'js/core/angularjs-toaster/toaster.js',
                            'js/core/angularjs-toaster/toaster.css'
                        ]
                    },
                    {
                        name: 'textAngular',
                        files: [
                            'js/vendor/modules/textAngular/textAngular-sanitize.min.js',
                            'js/vendor/modules/textAngular/textAngular.min.js'
                        ]
                    },
                    {
                        name: 'vr.directives.slider',
                        files: [
                            'js/vendor/angular/angular-slider/angular-slider.min.js',
                            'js/vendor/angular/angular-slider/angular-slider.css'
                        ]
                    },
                    {
                        name: 'com.2fdevs.videogular',
                        files: [
                            'js/vendor/modules/videogular/videogular.min.js'
                        ]
                    },
                    {
                        name: 'com.2fdevs.videogular.plugins.controls',
                        files: [
                            'js/vendor/modules/videogular/plugins/controls.min.js'
                        ]
                    },
                    {
                        name: 'com.2fdevs.videogular.plugins.buffering',
                        files: [
                            'js/vendor/modules/videogular/plugins/buffering.min.js'
                        ]
                    },
                    {
                        name: 'com.2fdevs.videogular.plugins.overlayplay',
                        files: [
                            'js/vendor/modules/videogular/plugins/overlay-play.min.js'
                        ]
                    },
                    {
                        name: 'com.2fdevs.videogular.plugins.poster',
                        files: [
                            'js/vendor/modules/videogular/plugins/poster.min.js'
                        ]
                    },
                    {
                        name: 'com.2fdevs.videogular.plugins.imaads',
                        files: [
                            'js/vendor/modules/videogular/plugins/ima-ads.min.js'
                        ]
                    }
                ]
            });
        }])
    ;
});