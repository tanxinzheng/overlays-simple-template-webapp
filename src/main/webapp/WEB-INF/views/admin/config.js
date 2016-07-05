/**
 * Created by M on 2014/10/29.
 */
'use strict';

require.config({
    paths:{
        ngApp:"views/app",
        amsRest:"views/service/rest_module",
        EnvModule:"views/env_config",
        ugPagination:"../static/js/ug-ui/ug-pagination",
        ngPermission:"../static/js/components/angular-permission/dist/angular-permission",
        //ngPermission:"js/libs/angular-permission/angular-permission",
        checklistModel:"../static/js/plugin/checklist-model/checklist-model",
        jqValidate:"../static/js/plugin/jquery-validate/jquery.validate.min",
        messageTip:"../static/js/plugin/jquery-validate/messages_zh",
        ngResource:"../static/js/libs/angular-resource",
        ugValidate:"../static/js/ug-ui/ug-validate",
        ugDialog:"../static/js/ug-ui/ug-dialog",
        select2:"../static/js/plugin/select2/select2.min",
        jquery:"../static/js/libs/jquery-2.0.2.min"
    },
    map:{
        "*":{
            css:"js/css"
        }
    },
    shim:{
        messageTip:{
            deps:["jqValidate"]
        },
        ugValidate:{
            deps:["jqValidate", "messageTip"]
        },
        amsRest:{
            deps:["ngResource"]
        },
        ngApp:{
            deps:["ugPagination", "EnvModule", "ugValidate", "amsRest", "ugDialog", "select2", "checklistModel"]
        }
    }
});
