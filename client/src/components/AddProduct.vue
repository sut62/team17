<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="4">
        <p>Employee</p>

        <v-select
          class="my-2"
          outlined
          :readonly="true"
          v-model="add_product.employeeId"
          :items="employees"
          label="Employee"
          item-text="username"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" sm="4">
        <p>Brand</p>

        <v-select
          class="my-2"
          outlined
          v-model="add_product.brandId"
          :items="brands"
          label="Brand"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Type Product</p>

        <v-select
          class="my-2"
          outlined
          v-model="add_product.type_productId"
          :items="type_products"
          label="Type Product"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-select>
      </v-col>
    </v-row>

    <v-row>
      <v-col cols="12" sm="4">
        <p>Quantity</p>

        <v-text-field
            
            solo
            outlined
            v-model="add_product.quantity"
            label="Quantity"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Price</p>
          <v-text-field
            solo
            outlined
            v-model="add_product.price"
            label="Price"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
        </v-col>
    </v-row>

    <v-btn rounded style="margin: 10px ; background-color: #00C853" @click="saveadd_product" dark>Add Product
        <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #E53935" @click="clear" dark>CLEAR
        <v-icon dark right>mdi-cancel</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #000000" @click="Logout" dark>LOG OUT
        <v-icon dark right>mdi-logout</v-icon>
      </v-btn>
    
    
  </v-container>
</template>

<script>
import http from "../Api";
export default {
  name: "add_product",
  data() {
    return {
      add_product: {
        employeeId: "",
        brandId: "",
        type_productId: "",
        quantity: "",
        price: ""
      },
      employees: [],
      brands: [],
      type_products: [],
      emid: -99,
      lock:false
      
    };
  },
  methods: {
    lockemployee(){
      this.emid = this.$route.params.em;
      this.add_product.employeeId  = this.emid;
      this.lock = true;
    },
    /*back(){
      this.$router.push({name: 'Dashboard' , params: {em: this.emid} }); 
    },*/
    Logout(){
      this.$router.push("/")
    },
    /* eslint-disable no-console */
    getEmployee() {
      http
        .get("/Employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBrand() {
      http
        .get("/brand")
        .then(response => {
          this.brands = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getType_Product() {
      http
        .get("/type_Product")
        .then(response => {
          this.type_products = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
   
    // function เมื่อกดปุ่มบันทึกข้อมูล
    saveadd_product() {
      if (
        !this.add_product.brandId ||
        !this.add_product.type_productId ||
        !this.add_product.quantity ||
        !this.add_product.price
      ) {
        alert("กรุณากรอกข้อมูลให้ครบ!");
      } else {
        http
          .post(
            "/add_product/" +
              this.add_product.employeeId +
              "/" +
              this.add_product.brandId +
              "/" +
              this.add_product.type_productId +
              "/" +
              this.add_product.quantity +
              "/" +
              this.add_product.price,
            this.add_product
          )
          .then(response => {
            alert("ทำรายการเสร็จสิ้น");
            console.log(response);
          })
          .catch(e => {
            console.log(e);
          });
      }
    },
    // function เมื่อกดปุ่มล้างข้อมูล
    clear() {
      this.add_product.employeeId = null;
      this.add_product.brandId = null;
      this.add_product.type_productId = null;
      this.add_product.quantity = null;
      this.add_product.price = null;
  },
    refreshList() {
    this.getEmployee();
    this.getBrand();
    this.getType_Product();
    }
   
   /* eslint-enable no-console */
  },
  
  mounted() {
    this.getEmployee();
    this.getBrand();
    this.getType_Product();
    this.lockemployee();
  }
};
</script>