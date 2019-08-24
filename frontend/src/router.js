import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import ProductList from "./views/ProductList.vue";
import ProductCreate from "./views/ProductCreate.vue";
import ProductShow from "./views/ProductShow.vue";
import ProductEdit from "./views/ProductEdit.vue";
import ProductHardDelete from "./views/ProductHardDelete.vue";
import Login from "./views/Login.vue";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: "/",
      name: "home",
      component: Home
    },
    { path: "/product-list", component: ProductList, name: "product-list" },
    {
      path: "/product/:id",
      component: ProductShow,
      name: "product-show",
      props: true
    },    
    {
      path: "/product/:index",
      component: ProductEdit,
      name: "product-edit",
      props: true
    },    
    {
      path: "/product/:index",
      component: ProductHardDelete,
      name: "product-hard-delete",
      props: true
    },    
    {
      path: "/product-create",
      component: ProductCreate,
      name: "product-create"
    },
    {
      path: "/login",
      name: "login",
      component: Login
    }
  ]
  // mode: "history"
});
