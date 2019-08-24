import styles from './assets/styles/common.css';

import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import VueSession from "vue-session";
import VueSweetalert2 from "vue-sweetalert2";
import Vuelidate from "vuelidate";

import BootstrapVue from 'bootstrap-vue';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = true;

// ----------- vue-session
var options = {
  persist: true
};
Vue.use(VueSession, options);

// ----------- modal
Vue.use(VueSweetalert2);

// ----------- Vuelidate
Vue.use(Vuelidate);

// --------Bootstrap
Vue.use(BootstrapVue);

new Vue({
  beforeCreate: function() {
    this.$session.start();

    this.$session.set("logged", 'false'); // !!!!!!!!!! change to false

    var categories = new Set(["0", "1", "2"]);
    if (!categories.has(this.$session.get("selected"))) {
      this.$session.set("selected", '0');
    }  
  },
  router,
  render: h => h(App)
}).$mount(`#app`);
