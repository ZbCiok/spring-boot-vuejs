// Consts.js

const serverUrl = "http://zbciok.eu:8080/";
// const serverUrl = "http://localhost:8080/";

const appName = "spring-boot-vuejs";

const baseURL = serverUrl + appName;

const imagesUrl = serverUrl + appName + "/images/";
const categoryUrl = serverUrl + appName + "/products/category/";

var categoryUrlArray = [categoryUrl + "tank",
                        categoryUrl + "bomber",
                        categoryUrl + "warship"
                       ];

var imagesUrlArray = [imagesUrl + "tank/",
                      imagesUrl + "bomber/",
                      imagesUrl + "warship/"
                   ];                    

export default { 
    serverUrl,
    appName,
    baseURL, 
    categoryUrlArray,
    imagesUrlArray,

    login: "YOUR LOGIN"
};


  
