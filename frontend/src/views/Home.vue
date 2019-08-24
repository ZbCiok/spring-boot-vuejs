<template>
  <layout name="LayoutDefault">
    <div>
      <h2 class="home">Home</h2>

       <div class="parent">
        <div class="col-left">
          <img v-bind:src="homeImage" border="0" class="img-class"/>
          <span style="font-size: 12px;">Soldier of Polish Infantry 1939.</span>
        </div>
        <div class="col-right">
          <span class="military-equipment-1">Military Equipment:</span>
          <p>
            <span style="color: red;">Spring Boot & VueJS & Bootstrap</span> application that lists samples of military equipment. 
            <br /><br />The application is not yet complete, other functionalities will later be implemented.
          </p>
        </div>
      </div>

      <h4 class="home">The descriptions and photos contained in the application were taken from Wikipedia.</h4>
    </div>
  </layout>
</template>

<script>
import Layout from "@/layouts/Layout";
import Consts from "../consts/Consts";

export default {
  name: "Home",
  components: {
    Layout
  },

  data() {
    return {
      homeImage: '',
    };
  },

  created() {
    this.homeImage = Consts.serverUrl + Consts.appName + "/images/others/Soldier_of_Polish_Infantry_1939.jpg";
  }
};
</script>

<style>
.img-class {
  margin-bottom: 0px;
  width: 100%;
}
h2.home {
  margin-bottom: 2em;
}
h4.home {
  font-size: 18px;
  color: royalblue;
  font-style: italic;
  margin-top: 80px;
}
span.military-equipment-1 {
  font-size: 20px;
  font-weight: bold;
  font-style:italic
}

.parent {
  display: flex;
  margin-bottom: 1em;
}
.col-left {
  
  width: 200px;
}
.col-right {
  flex: 1;
  margin-left: 1em;
  margin-top: 0em;
}
</style>
