/**
 * Created by tanxinzheng on 16/11/23.
 */
'use strict';

describe('myApp.version module', function() {
    beforeEach(module('myApp.version'));

    describe('version service', function() {
        it('should return current version', inject(function(version) {
            expect(version).toEqual('0.1');
        }));
    });
});
