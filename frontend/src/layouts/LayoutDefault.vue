<template>
  <div class="LayoutDefault">  
    <nav class="LayoutDefault__nav">
      <router-link to="/">Home</router-link>&nbsp;&nbsp;
      <router-link to="/product-list">List</router-link>&nbsp;&nbsp;
      <router-link to="/product-create">Create</router-link>&nbsp;&nbsp;
      <router-link to="/login">Login</router-link>&nbsp;&nbsp;
      <div class="LayoutDefault__user">{{ user ? user.name : `Loading...` }}</div>
    </nav>

    <main class="LayoutDefault__main">
      <slot />
    </main>

    <footer
      class="LayoutDefault__footer"
    >VueJS example: military-equipment version 0.2.4, author Zbigiew Ciok, PL</footer>
  </div>
</template>

<script>
export default {
  name: `LayoutDefault`,
  data() {
    return {
      user: null
    };
  },
  created() {
    // Simulate fetching user data.
    setTimeout(() => {
      this.user = { name: `John Doe` };
    }, 2000);
  }
};
</script>

<style lang="scss">
.LayoutDefault {
  max-width: 72em;
  margin-right: auto;
  margin-left: auto;
  padding-right: 1em;
  padding-left: 1em;

  &__nav {
    /* sticky */
    position: -webkit-sticky;
    position: sticky;
    top: 0.1em;

    padding-top: 1px;
    padding-bottom: 18px;
    background-color: rgba(189, 183, 108, 1.00);
    border: 1px solid #000000;

    padding-left: 1em;
    padding-right: 1em;

    /* box shadow */
    -webkit-box-shadow: 6px 7px 8px 0px rgba(120, 120, 120, 1);
    -moz-box-shadow: 6px 7px 8px 0px rgba(120, 120, 120, 1);
    box-shadow: 6px 7px 8px 0px rgba(120, 120, 120, 1);
  }

  &__nav a {
    float: left;
    display: block;
    color: #fff;
    text-align: center;
    padding: 8px 16px;
    text-decoration: none;
  }

  &__nav a:hover {
    background-color: rgba(255, 255, 255, 0.37);
    color: black;
  }

  &__user {
    padding-top: 9px;
    float: right;
  }

  &__main {
    padding-top: 1.5em;
    padding-bottom: 2em;
  }

  &__footer {
    padding-top: 0.2em;
    padding-bottom: 0.2em;
    background: rgba(189, 183, 108, 0.99);
    border: 1px solid #000000;
    text-align: center;
    font-size: 12px;
    margin-bottom: 3em;
  }
}
</style>

