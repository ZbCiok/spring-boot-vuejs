<template>
  <layout name="LayoutDefault">
    <div class="list-pos">
      <h2 class="list">List of Objects</h2>
      <br />
      <b-alert :show="loading" variant="info">Loading...</b-alert>
      <form @submit.prevent="formSubmit">
        <table>
          <tr>
            <td>
              <span>Select category:&nbsp;&nbsp;</span>
            </td>
            <td width="150px;">
              <select v-model="selected" class="form-control">
                <option value="0">Tanks</option>
                <option value="1">Bombers</option>
                <option value="2">Warships</option>
              </select>
            </td>
            <td width="70px;" align="right">
              <b-button type="submit" variant="info" size="sm">Submit</b-button>
            </td>
          </tr>
        </table>
      </form>
      <br />

      <table class="list">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Country</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(product, index) in products" v-bind:key="index">
            <td width="4%">{{ product.id }}</td>
            <td width="10%">{{ product.name }}</td>
            <td>{{ product.description }}</td>
            <td>{{ product.country }}</td>
            <td width="280px">
              <div class="action-center">
                <div class="action">
                  <b-button
                    variant="info"
                    size="sm"
                    :to="{ name: 'product-show', params: { id: index } }"
                    tag="button"
                  >Show</b-button>
                </div>
                <div class="action">
                  <b-button
                    variant="info"
                    size="sm"
                    :to="{ name: 'product-edit', params: { index: index } }"
                    tag="button"
                  >Edit</b-button>
                </div>
                <div class="action">
                  <b-button
                    variant="info"
                    size="sm"
                    :to="{ name: 'product-hard-delete', params: { index: index } }"
                    tag="button"
                  >Hard&nbsp;Delete</b-button>
                </div>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </layout>
</template>

<script>
import Consts from "@/consts/Consts.js";
import Layout from "@/layouts/Layout";
import axios from "axios";
// import ProductService from "@/services/ProductService.js";

export default {
  name: `Home`,
  components: {
    Layout
  },

  data() {
    return {
      selected: "0",
      loading: false,
      products: []
    };
  },

  created() {
    this.selected = this.$session.get("selected");
    this.loading = true;
    this.getCategoryProducts(this.selected);
    this.loading = false;  
  },

  methods: {
    formSubmit(e) {
      this.loading = true;
      this.getCategoryProducts(this.selected);
      this.loading = false;
      this.$session.set("selected", this.selected);
    },

    async getCategoryProducts(categoryInd) {
      try {
        const response = await axios.get(Consts.categoryUrlArray[categoryInd]);
        this.products = response.data;
      } catch (error) {
        console.error("Error 01: " + error);
      }
    }
  }
};
</script>

<style>
table.list {
  font-size: 14px;
  border-collapse: collapse;
}
table.list th {
  text-align: center;
  border-bottom: 1px solid #ccc;
}
table.list td {
  font-size: 14px;
  padding-left: 1em;
  padding-right: 1em;
  padding-top: 0.3em;
  padding-bottom: 0.3em;
  text-align: left;
  border-bottom: 1px solid #ccc;
}
table.list thead tr {
  background: #ddd;
  top: 52px;
  position: sticky;
}
table.list tbody tr:hover {
  background: rgba(204, 204, 204, 0.466);
}

div.action-center {
  text-align: center;
}

div.action {
  display: inline;
  padding-right: 1px;
}
</style>