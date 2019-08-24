<template>
  <layout name="LayoutDefault">
    <div>
      <h2 class="show">Show Object</h2>
      <br />
      <table>
        <tr>
          <td class="td1">Id:</td>
          <td>
            <input class="td2" type="text" :disabled="true" v-model="product.id" />
          </td>
        </tr>
        <tr>
          <td class="td1">Name:</td>
          <td>
            <input class="td2" type="text" :disabled="true" v-model="product.name" />
          </td>
        </tr>
        <tr>
          <td class="td1">Description:</td>
          <td>
            <textarea class="td2" :disabled="true" v-model="product.description" />
          </td>
        </tr>
        <tr>
          <td class="td1">Country:</td>
          <td>
            <input class="td2" type="text" :disabled="true" v-model="product.country" />
          </td>
        </tr>
      </table>
      <div class="img-div">
        <img v-bind:src="product.image" class="img05" />
      </div>
      <br />
      <b-button variant="info" size="sm" :to="{ name: 'product-list'}" tag="button">Exit</b-button>
    </div>
  </layout>
</template>

<script>
import ProductService from "@/services/ProductService.js";
import Consts from "@/consts/Consts.js";
import Layout from "@/layouts/Layout";

export default {
  name: "ProductShow",
  components: {
    Layout
  },

  image: "",
  props: ["id"],
  data() {
    return {
      product: []
    };
  },

  created() {
    this.selected = this.$session.get("selected");

    ProductService.getProducts(Consts.categoryUrlArray[this.selected])
      .then(response => {
        this.product = response.data[this.id];
        this.product.image =
          Consts.imagesUrlArray[this.selected] + this.product.image;
      })
      .catch(error => {
        console.log("ProductShow: There was an error:", error.response);
      });
  }
};
</script>

<style>
.img-div {
  width: auto;
  text-align: center;
}
img.img05 {
  color: black;
  border-style: solid;
  border-width: 1px;
  width: 50%;
  height: 50%;
  object-fit: contain;

  margin-top: 1em;
}

.td1 {
  border: solid 0px #ddd;
  margin-right: 10px;
  padding-right: 14px;
  vertical-align: middle;
  width: 3%;
}

.td2 {
  width: 98%;
  text-align: left;
  font-size: 16px;
  margin-bottom: 4px;
}
</style>
