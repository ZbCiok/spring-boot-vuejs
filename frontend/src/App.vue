<template>
  <div>
    <p class="startup">Startup Spring Boot & VueJS.</p>
    <component :is="layout">
      <router-view :layout.sync="layout" />
    </component>
  </div>
</template>

<script>
export default {
  name: `App`,
  data() {
    return {
      layout: `div`
    };
  }
};
</script>

<style lang="scss">
.startup {
  color: rgba(0, 0, 0, 0.2);
  font-size: 48px;
  position: absolute;
  transform: translate(-250px, 350px) rotate(-90deg);
}

* {
  margin: 0;
  padding: 0;
}

html {
  line-height: 1.6;
  color: #333;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", "Roboto", "Oxygen",
    "Ubuntu", "Cantarell", "Fira Sans", "Droid Sans", "Helvetica Neue",
    sans-serif;
}

p {
  &:not(:first-child) {
    margin-top: 1.25em;
  }
}
</style>
