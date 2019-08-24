<template>
  <div>
    <!-- Renders "Hello from the parent!" -->
    <child-component>
      <p>Hello from the parent!</p>
    </child-component>
    <!-- Renders "Hello from the child!" -->
    <child-component></child-component>
  </div>
</template>

<script>
import ChildComponent from "@/components/ChildComponent.vue";

export default {
  components: {
    ChildComponent
  }
};
</script>