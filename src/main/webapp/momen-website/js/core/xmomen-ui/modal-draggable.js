/**
 * Created by tanxinzheng on 16/7/3.
 */
define(function(){
    return angular.module("xmomen.draggable",[

    ]).directive('draggable', ['$document', function($document) {
        return function(scope, elem, attr) {
            var startX = 0, startY = 0, x = 0, y = 0;
            var element = elem.parent().parent();

            element.css({
                position: 'relative',
                cursor: 'move'
            });

            elem.on('mousedown', function(event) {
                // Prevent default dragging of selected content
                event.preventDefault();
                startX = event.pageX - x;
                startY = event.pageY - y;
                $document.on('mousemove', mousemove);
                $document.on('mouseup', mouseup);
            });

            function mousemove(event) {
                y = event.pageY - startY;
                x = event.pageX - startX;
                element.css({
                    top: y + 'px',
                    left:  x + 'px'
                });
            }

            function mouseup() {
                $document.off('mousemove', mousemove);
                $document.off('mouseup', mouseup);
            }
        };
    }]);
});