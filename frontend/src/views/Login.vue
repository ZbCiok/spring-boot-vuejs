<template>
  <layout name="LayoutDefault">
    <div>
      <h1>Login</h1>
      <span class="info">User not logged in can only read data.</span>
      <br />
      <br />
      <form @submit.prevent="formSubmit">
        Login:&nbsp;
        <input v-model="password" type="password" />&nbsp;
        <b-button type="submit" variant="info" size="sm">Login</b-button>
      </form>
      <br />
    </div>
  </layout>
</template>

<script>
import Layout from "../layouts/Layout";
import Consts from "@/consts/Consts.js";

export default {
  name: `Login`,
  components: {
    Layout
  },

  data() {
    return {
      password: ""
    };
  },

  methods: {
    formSubmit(e) {
      if (Consts.login == this.password) {
        this.$session.set("logged", 'true');
      } else {
        this.$session.set("logged", 'false');
      };
      
      this.$router.push({ name: "product-list" });
    }
  }
};
</script>

<style>
  span.info {
    font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
    font-size: 14px;
    color: red;
  }
</style>