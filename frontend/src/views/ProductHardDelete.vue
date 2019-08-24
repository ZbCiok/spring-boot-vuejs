<template>
  <layout name="LayoutDefault">
    <div>
      <h2 class="hard-delete">Hard Delete Object</h2>
      <br />
      <h5 class="hard-delete-cancel">'Hard Delete' cannot be undone.</h5>
      <b-alert :show="loading" variant="info">Loading...</b-alert>
      <form @submit.prevent="formSubmit">
        <table class="outer-hard-delete">
          <tr>
            <td>
              <table class="hard-delete">
                <tr>
                  <td class="td-hdelete-1">Id:</td>
                  <td>
                    <input
                      style="width: 420px;"
                      type="text"
                      :disabled="true"
                      class="input-field"
                      v-model="product.id"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Name:</td>
                  <td>
                    <input
                      style="width: 420px;"
                      type="text"
                      :disabled="true"
                      class="input-field"
                      v-model="product.name"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Description:</td>
                  <td>
                    <textarea
                      style="width: 420px;"
                      :disabled="true"
                      class="input-field-text"
                      v-model="product.description"
                    />
                  </td>
                </tr>
                <tr>
                  <td>Country:</td>
                  <td>
                    <input
                      style="width: 420px;"
                      type="text"
                      :disabled="true"
                      class="input-field"
                      v-model="product.country"
                    />
                  </td>
                </tr>
              </table>
            </td>
            <td width="20px;">&nbsp;</td>
            <td align="right" width="460px;">
              <div class="img-div">
                <img v-bind:src="tmpProductImage" class="img1" />
              </div>
            </td>
          </tr>
        </table>
        <br />
        <b-button type="submit" variant="info" size="sm" v-bind:disabled="isButtonDisabled">Delete</b-button>
        <span style='color: red;' v-if="labelDeletedVisible">&nbsp;Deleted</span>
        &nbsp;
        <b-button variant="info" size="sm" :to="{ name: 'product-list'}" tag="button">Exit</b-button>          
      </form>
    </div>
  </layout>
</template>

<script>
import ProductService from "@/services/ProductService.js";
import Consts from "@/consts/Consts.js";
import Layout from "@/layouts/Layout";
import axios from "axios";

export default {
  name: "ProductDelete",
  components: {
    Layout
  },

  props: ["index"],
  tmpProductImage: "",
  data() {
    return {
      isButtonDisabled: true,
      product: {},
      loading: false,
      categoryUrl: '',

      labelDeletedVisible: false
    };
  },

  created() {
    this.selected = this.$session.get("selected");
    this.categoryUrl = Consts.categoryUrlArray[this.selected];
    this.getCategoryProduct(this.categoryUrl, this.index);

    if (this.$session.get("logged") == "false") {
      this.isButtonDisabled = true;
    } else {
      this.isButtonDisabled = false;
    }
  },

  methods: {

    async getCategoryProduct(categoryUrl, productInd) {
      this.loading = true;

      try {
        const response = await axios.get(categoryUrl);
        this.product = response.data[productInd];
        this.tmpProductImage =
            Consts.imagesUrlArray[this.$session.get("selected")] +
            this.product.image;  
      } catch (error) {
        console.error("Error 01: " + error);
      }   

      this.loading = false;
    },      

    formSubmit(e) {
      this.dialogConfirm();
    },

    dialogConfirm() {
      this.$swal({
        animation: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        backdrop: true,

        title: "Delete?",
        text: "",
        type: "question",
        showCancelButton: true,
        confirmButtonColor: "#33a5ff ",
        cancelButtonColor: "#33a5ff ",
        confirmButtonText: "Yes",
        cancelButtonText: "No"
      }).then(result => {
        if (result.value) {
          this.deleteCategoryProduct(this.product);
          this.isButtonDisabled = true;
          this.labelDeletedVisible = true;
        }
      });
    },

    async deleteCategoryProduct(product) {
      try {
        const response = await axios.delete(
          Consts.baseURL + "/products/" + this.product.id
        );
        this.products = response.data;
      } catch (error) {
        console.error("Error 01: " + error);
      }
    }
  }
};
</script>

<style>
.img-div {
  width: auto;
  text-align: center;
}
img.img1 {
  color: black;
  border-style: solid;
  border-width: 1px;
  width: 100%;
  height: 100%;
  object-fit: contain;

  margin-top: 1em;
}

h2.hard-delete {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
h5.hard-delete-cancel {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
  font-style: italic;
  color: red;
}

.td-hdelete-1 {
  vertical-align: middle;
  width: 120px;
}
</style>


