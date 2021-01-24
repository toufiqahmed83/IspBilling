/**
 * Created by Administrator on 3/4/2019.
 */

//
//function autocom (val, getvalue ,dispfield, dbfield){
//    var variableOfFunction1 = val;
//    var gvalue=getvalue;
//    var display=dispfield;
//    var displayid=dbfield;
//    var l_url=window.location.origin;
//    //alert(variableOfFunction1+l_url);
//    var options = {
//        minCharNumber:2,
//        url: l_url+variableOfFunction1,
//        getValue:gvalue,
//        list: {
//
//            match: {
//                enabled: true,
//
//                method:  function(element, phrase) {
//                    if(element.indexOf(phrase) === 0) {
//                        return true;
//                    } else {
//                        return false;
//                    }
//                }
//
//            },
//
//
//            onSelectItemEvent: function() {
//                var value = $(display).getSelectedItemData().id;
//
//                $(displayid).val(value).trigger("change");
//            }
//        }
//    };
//
//    $(display).easyAutocomplete(options, "minLength", 2);
//    //alert(l_url);
//};


function autocom (val, getvalue ,dispfield, dbfield){
    var variableOfFunction1 = val;
    var gvalue=getvalue;
    var display=dispfield;
    var displayid=dbfield;
    var l_url=window.location.origin;
    //alert(variableOfFunction1+l_url);
    var options = {
        minCharNumber:2,
        url: l_url+variableOfFunction1,
        getValue:gvalue,
        list: {

            match: {
                enabled: true,

                method:  function(element, phrase) {
                    if(element.indexOf(phrase) === 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            },


            onSelectItemEvent: function() {
                var value = $(display).getSelectedItemData().id;

                $(displayid).val(value).trigger("change");
            }
        }
    };

    $(display).easyAutocomplete(options, "minLength", 2);
    //alert(l_url);
};

function autocomwithtemplate (val, getvalue ,dispfield, dbfield, template){
    var variableOfFunction1 = val;
    var gvalue=getvalue;
    var display=dispfield;
    var displayid=dbfield;
    var vtemplate=template;
    var l_url=window.location.origin;
    //alert(vtemplate);
    //alert(gvalue);
    var options = {
        minCharNumber:2,
        url: l_url+variableOfFunction1,
        getValue:gvalue,
        template: {
            type: "description",
            //type: "description1",
            fields: {
                description:template
            }


        },

        list: {

            match: {
                enabled: true,

                method:  function(element, phrase) {
                    if(element.indexOf(phrase) === 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            },


            onSelectItemEvent: function() {
                var value = $(display).getSelectedItemData().id;

                $(displayid).val(value).trigger("change");
            }
        }
    };

    $(display).easyAutocomplete(options, "minLength", 2);
    //alert(l_url);
};



function autocomwithobject (val, getvalue ,dispfield, dbfield,vtemplate ,indicator ){

    //alert(val);
    var variableOfFunction1 = val;
    var gvalue=getvalue;
    var display=dispfield;
    var displayid=dbfield;
    var template=vtemplate;
    var lindicator=indicator;


    var l_url=window.location.origin;

    var options = {
        minCharNumber:2,
        url: l_url+variableOfFunction1,

        getValue:gvalue,

        template: {
            type: "description",
            //type: "description1",
            fields: {
                description:template
            }


        },
        list: {

            match: {
                enabled: true,

                method:  function(element, phrase) {
                    if(element.indexOf(phrase) === 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            },


            onSelectItemEvent: function() {
                var value = $(display).getSelectedItemData();

                var myJSON = JSON.stringify(value);
                //window.location = "http://localhost:9091/AYE/OM/OrgAdd/E/5/5" + myJSON;
                obj = JSON.parse(myJSON);



                //document.getElementById("demo").innerHTML =
                //    obj.id + " " + obj.code;

                //alert(customattribute+'xxx');


                selectedobj (value,lindicator);



            }

        }
    };

    $(display).easyAutocomplete(options, "minLength", 2);
    //alert(l_url);
};





function autocomselctedmulti (val1, getvalue1 ,dispfield1, dbfield1, dbfield2,dbfield3){
    var variableOfFunction1 = val1;
    var gvalue=getvalue1;
    var display=dispfield1;
    var displayid=dbfield1;
    var displayid2=dbfield2;
    var displayid3=dbfield3;
    var l_url=window.location.origin;
    var options = {
        minCharNumber:2,
        url: l_url+variableOfFunction1,
        getValue:gvalue,
        list: {

            match: {
                enabled: true,

                method:  function(element, phrase) {
                    if(element.indexOf(phrase) === 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            },


            onSelectItemEvent: function() {
                var value = $(display).getSelectedItemData().id.id;
                var value1 = $(display).getSelectedItemData().id.orgId.id;
                var value2 = $(display).getSelectedItemData().defaultUom.id;

                $(displayid).val(value).trigger("change");
                $(displayid2).val(value1).trigger("change");
                $(displayid3).val(value2).trigger("change");
            }
        }
    };

    $(display).easyAutocomplete(options, "minLength", 2);
};

function autocoTrnsUom (val, getvalue ,dispfield, dbfield){
    var variableOfFunction1 = val;
    var gvalue=getvalue;
    var display=dispfield;
    var displayid=dbfield;
    var l_url=window.location.origin;
    //alert(variableOfFunction1+l_url);
    var options = {
        minCharNumber:2,
        url: l_url+variableOfFunction1,
        getValue:gvalue,
        list: {

            match: {
                enabled: true,

                method:  function(element, phrase) {
                    if(element.indexOf(phrase) === 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            },


            onSelectItemEvent: function() {
                var value = $(display).getSelectedItemData().toUom.id;

                $(displayid).val(value).trigger("change");
            }
        }
    };

    $(display).easyAutocomplete(options, "minLength", 2);
    //alert(l_url);
};
function autocomCodeCome (val1, getvalue2 ,dispfield2, dbfield3, dbfield4){
    var variableOfFunction1 = val1;
    var gvalue=getvalue2;
    var display=dispfield2;
    var displayid=dbfield3;
    var displayid2=dbfield4
    //var displayid3=dbfield3;
    var l_url=window.location.origin;
    var options = {
        minCharNumber:2,
        url: l_url+variableOfFunction1,
        getValue:gvalue,
        list: {

            match: {
                enabled: true,

                method:  function(element, phrase) {
                    if(element.indexOf(phrase) === 0) {
                        return true;
                    } else {
                        return false;
                    }
                }

            },


            onSelectItemEvent: function() {
                var value = $(display).getSelectedItemData().id;
                var value1 = $(display).getSelectedItemData().source;
                //var value2 = $(display).getSelectedItemData().defaultUom.id;

                $(displayid).val(value).trigger("change");
                $(displayid2).val(value1).trigger("change");
                //$(displayid3).val(value2).trigger("change");
            }
        }
    };

    $(display).easyAutocomplete(options, "minLength", 2);
}