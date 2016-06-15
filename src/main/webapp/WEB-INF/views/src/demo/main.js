/**
 * Created by M on 2014/10/29.
 */
require.config({
  paths:{
    ngAccount:"views/app"
  }
});
require(["ngAccount"], function(ngAccount) {
  angular.bootstrap(document,['UDF.account']);
});