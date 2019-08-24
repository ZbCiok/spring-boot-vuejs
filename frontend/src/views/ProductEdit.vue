<template>
  <layout name="LayoutDefault">
    <div>
      <h2 class="show">Edit Object</h2>
      <b-alert :show="loading" variant="info">Loading...</b-alert>
      <form @submit.prevent="formSubmit">
        <table class="outer-show">
          <tr>
            <td style="width: 560px;">
              <table class="show">
                <tr>
                  <td class="td-edit-1">Id:</td>
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
                  <td class="td-edit-1">Name:</td>
                  <td>
                    <div style="width: 420px;">
                      <input
                        style="width: 420px;"
                        v-model="$v.product.name.$model"
                        type="text"
                        class="input-field"
                      />

                      <span
                        class="error-invalid-form"
                        v-if="!$v.product.name.required"
                      >Name is required between 3 and 100 characters..</span>

                      <span
                        class="error-invalid-form"
                        v-if="!$v.product.name.minLength"
                      >Name must have at least {{ $v.product.category.$params.minLength.min }} characters.</span>

                      <span
                        class="error-invalid-form"
                        v-if="!$v.product.name.maxLength"
                      >Name must have a maximum of {{ $v.product.name.$params.maxLength.max }} characters.</span>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td class="td-edit-1">Description:</td>
                  <td>
                    <div style="width: 420px;">
                      <textarea
                        style="width: 420px;"
                        v-model="$v.product.description.$model"
                        class="input-field-text"
                      />

                      <span class="error-invalid-form" v-if="!$v.product.description.maxLength">
                        <br />
                        Description must have a maximum of {{ $v.product.description.$params.maxLength.max }} characters.
                      </span>
                    </div>
                  </td>
                </tr>
                <tr>
                  <td class="td-edit-1">Country:</td>
                  <td>
                    <div>
                      <input
                        style="width: 420px;"
                        v-model="$v.product.country.$model"
                        type="text"
                        class="input-field"
                      />

                      <span class="error-invalid-form" v-if="!$v.product.country.maxLength">
                        <br />
                        Country must have a maximum of {{ $v.product.country.$params.maxLength.max }} characters.
                      </span>
                    </div>
                  </td>
                </tr>
                <!--
                <tr>
                  <td>Image:</td>
                  <td>
                    <input
                      type="text"
                      :disabled="false"
                      class="input-field"
                      v-model="product.image"
                    />
                  </td>
                </tr>
                -->
              </table>
            </td>
            <td>
              <div class="img-div">
                <img v-bind:src="tmpProductImage" class="img1" />
              </div>
            </td>
          </tr>
        </table>
        <br />
        <b-button type="submit" variant="info" size="sm" v-bind:disabled="isButtonDisabled">Save</b-button>
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
import { required, minLength, maxLength } from "vuelidate/lib/validators";
import axios from "axios";

export default {
  name: "ProductEdit",
  components: {
    Layout
  },

  props: ["index"],
  data() {
    return {
      loading: false,
      product: {},
      categoryUrl: '',
      isButtonDisabled: true,
      tmpProductImage: ""
    };
  },

  validations: {
    product: {
      name: { required, minLength: minLength(3), maxLength: maxLength(100) },
      description: { maxLength: maxLength(2000) },
      country: { maxLength: maxLength(50) },
      image: { maxLength: maxLength(250) }
    }
  },

  async created() {
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
    async formSubmit(e) {
      this.dialogConfirm();

      this.$v.$touch();
      if (this.$v.$invalid) {
        this.$swal.fire({
          title: "Fields validation error!",
          animation: false,
          allowOutsideClick: false,
          allowEscapeKey: false,
          allowEnterKey: false,
          backdrop: true
        });
        return;
      }
    },

    async putCategoryProduct(productUrl, product) {
      this.loading = true;

      try {
        const response = await axios.put(productUrl + "/" + this.product.id, product);
        this.product = response.data;

        console.log("Put >>> " + this.product.name);
      } catch (error) {
        console.error("Error 03: " + error);
      };        

      this.loading = false;
    },

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

    dialogConfirm() {
      this.loading = true;
      this.$swal({
        animation: false,
        allowOutsideClick: false,
        allowEscapeKey: false,
        allowEnterKey: false,
        backdrop: true,

        title: "Save data?",
        text: "",
        type: "question",
        showCancelButton: true,
        confirmButtonColor: "#33a5ff ",
        cancelButtonColor: "#33a5ff ",
        confirmButtonText: "Yes",
        cancelButtonText: "No"
      }).then(result => {
        if (result.value) {
          this.putCategoryProduct(
            Consts.categoryUrlArray[this.$session.get("selected")], this.product
          );
        }
      });
      this.loading = false;
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

table.outer-show td {
  border: solid 0px #ddd;
}

table.show {
  border: solid 0px #ddd;
  margin: 0px;
  padding: 0px;
  border-spacing: 0px;
  border-collapse: collapse;
  line-height: 22px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

.td-edit-1 {
  vertical-align: middle;
  width: 120px;
}

/* table.show td {
  border: solid 0px #ddd;
  margin-right: 10;
  padding-right: 14px;
} */

table.show tr {
  margin: 0;
  padding: 4px;
}

h2.show {
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

.input-field {
  width: 400px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}

.input-field-text {
  width: 414px;
  height: 200px;
  font-size: 16px;
  font-family: Arial, Verdana, Tahoma, Helvetica, sans-serif;
}
</style>
