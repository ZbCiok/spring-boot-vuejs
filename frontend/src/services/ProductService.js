import axios from "axios";
import Consts from "../consts/Consts";

const apiClient = axios.create({
  baseURL: Consts.baseURL,

  withCredentials: false,

  headers: {
    Accept: "application/json",
    "Content-Type": "application/json"
  }
});

export default {
  async execute(method, resource, data) {
    return apiClient({
      method,
      url: resource,
      data
    });
  },

  getProducts(url) {
    return this.execute("get", url);
  },
  putEvent(id, product) {
    return this.execute('put', id, product);
  },
  postEvent(product) {
    return apiClient.post("/products", product);
  },
  deleteEvent(id, product) {
    return apiClient.delete("/products/" + id);
  }
};
