<template>
  <v-container>
    <v-card color="#e6f7ff">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
         <h1 class="text mt-5 pt-5"
            style="text-align: center;
            font: 40px BankGothic Md BT, sans-serif; color: 	#602040 ;
            width: 100%;"><strong>Show Product</strong></h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8" >
        <v-data-table :headers="headers" :items="items" :items-per-page="5"  class="elevation-1" >
        </v-data-table>
      </v-col>
    </v-row>  
    <v-row justify="center">
      <v-btn rounded style="margin: 10px ; background-color: #002b80" @click="back" dark>Back
        <v-icon dark right>mdi-arrow-left-bold</v-icon>
    </v-btn>
    <v-btn rounded style="margin: 10px ; background-color: #000000" @click="Logout" dark>LOG OUT
        <v-icon dark right>mdi-logout</v-icon>
    </v-btn>
     </v-row>
     <v-col></v-col>
    </v-card>
  </v-container>
</template>

<script>
import http from "../Api";
export default {
  name: "ShowProduct",
  data() {
    return {
      headers: [
        {
          text: "ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "Employee", value: "employee.username" },
        { text: "Date", value: "date" },
        { text: "Brand", value: "brand.name" },
        { text: "Type Product", value: "type_product.name" },
        { text: "Quantity", value: "quantity" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox

    getProducts() {
      http
        .get("/product")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    Logout(){
      localStorage.setItem("id","");
      this.$router.push("/")
    },
    back(){
      this.$router.push("/Product")
    },
    refreshList() {
      this.getProducts();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getProducts();
  }
};
</script>
