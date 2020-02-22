import 'jquery';
//import 'bootstrap';
import '../css/layout.css';
import '../css/fonts.css';
import '../css/style.css';
//import 'bootstrap/dist/css/bootstrap.min.css';
//import 'html5shiv';
//import 'respond';

// forms..
function parseTextarea(form_id, val, input_field) {
    var pars = val.split('\n')
    for (var i in pars) {
        $('#' + form_id).append('<input type="hidden" name="' + input_field + '" value="' + pars[i] + '" />');
    }
}
