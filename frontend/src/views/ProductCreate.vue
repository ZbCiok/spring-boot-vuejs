<template>
  <layout name="LayoutDefault">
    <div>
      <h2>Create Object</h2>
      <br />
      <b-alert :show="loading" variant="info">Loading...</b-alert>
      <form @submit.prevent="formSubmit">
        <table class="create">
          <tr>
            <td class="td-create-1">Category:</td>
            <td>
              <div style="width: 420px;">
                <select v-model="selected" class="form-control">
                  <option
                    v-for="(option, i) in options"
                    v-bind:value="option.value"
                    v-bind:key="i"
                  >{{ option.text }}</option>
                </select>
              </div>
            </td>
          </tr>
          <tr>
            <td class="td-create-1">Name:</td>
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
            <td class="td-create-1">Description:</td>
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
            <td class="td-create-1">Country:</td>
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
          <tr>
            <td class="td-create-1">Image name:</td>
            <td>
              <div>
                <input
                  style="width: 420px;"
                  v-model="$v.product.image.$model"
                  type="text"
                  class="input-field"
                />

                <span class="error-invalid-form" v-if="!$v.product.image.maxLength">
                  <br />
                  Image name must have a maximum of {{ $v.product.image.$params.maxLength.max }} characters.
                </span>
              </div>
            </td>
          </tr>
        </table>
        <br />
        <b-button type="submit" variant="info" size="sm" v-bind:disabled="isButtonDisabled">Create</b-button>
        <span style='color: red;' v-if="labelCreatedVisible">&nbsp;Created</span>
        &nbsp;
        <b-button variant="info" size="sm" :to="{ name: 'product-list'}" tag="button">Exit</b-button>        
      </form>
    </div>
  </layout>
</template>

<script>
import { required, minLength, maxLength } from "vuelidate/lib/validators";
import ProductService from "@/services/ProductService.js";
import Consts from "@/consts/Consts.js";
import Layout from "@/layouts/Layout";
import axios from "axios";

export default {
  name: "ProductCreate",
  components: {
    Layout
  },

  name: "app",
  data() {
    return {
      isButtonDisabled: true,
      loading: false,
      selected: "tank",
      labelCreatedVisible: false,

      options: [
        { text: "Tanks", value: "tank" },
        { text: "Bombers", value: "bomber" },
        { text: "Warships", value: "warship" }
      ],

      product: {
        category: "",
        name: "",
        description: "",
        country: "",
        image: ""
      },

      submitted: false
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

  created() {
    if (this.$session.get("logged") == "false") {
      this.isButtonDisabled = true;
    } else {
      this.isButtonDisabled = false;
    }
  },

  methods: {
    formSubmit(e) {
      this.submitted = true;

      // stop here if form is invalid
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
      this.dialogConfirm();
    },

    dialogConfirm() {
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
          this.product.category = this.selected;
          this.createCategoryProduct(this.product);
          this.isButtonDisabled = true;
          this.labelCreatedVisible = true;
        }
      });
    },

    async createCategoryProduct(product) {
      try {
        const response = await axios.post(Consts.baseURL + "/products", product);
        this.products = response.data;
      } catch (error) {
        console.error("Error 01: " + error);
      }
    }    
  }
};
</script>

<style>
table.create {
  border: solid 0px #ddd;
  margin: 0px;
  padding: 0px;
  border-spacing: 0px;
  border-collapse: collapse;
  line-height: 22px;
  font-size: 16px;
}

table.create tr {
  margin: 0;
  padding: 4px;
}

.td-create-1 {
  vertical-align: middle; 
  width: 120px;
}

.input-field {
  margin-bottom: 0px;
  margin-top: 15px;
  width: 420px;
  font-size: 16px;
}
.input-field-text {
  margin-top: 15px;
  height: 200px;
  width: 420px;
  font-size: 16px;
}

.error-invalid-form {
  font-size: 12px;
  color: red;
}
</style>
