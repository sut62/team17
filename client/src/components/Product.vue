<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="4">
        <p>Employee</p>

        <v-select
          class="my-2"
          :readonly="true"
          outlined
          v-model="product.employeeId"
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
          v-model="product.brandId"
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
          v-model="product.type_productId"
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
        
            type="number"
            :min = "0"
            solo
            outlined
            v-model="product.quantity"
            label="Quantity"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Price</p>
          <v-text-field
          
            type="number"
            :min = "0"
            solo
            outlined
            v-model="product.price"
            label="Price"
            :rules="[(v) => !!v || 'Item is required']"
            required

          ></v-text-field>
        </v-col>
    </v-row>

    <v-btn rounded style="margin: 10px ; background-color: #00C853" @click="saveproduct" dark>Add Product
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
  name: "product",
  data() {
    return {
      product: {
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
      this.product.employeeId  = this.emid;
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
    saveproduct() {
      if (
        !this.product.brandId ||
        !this.product.type_productId ||
        !this.product.quantity ||
        !this.product.price
      ) {
        alert("กรุณากรอกข้อมูลให้ครบ!");
      }else if(this.product.quantity<0||this.product.price<0){
        alert("กรอกข้อมูลให้ถูกต้อง!");
      }else {
        http
          .post(
            "/product/" +
              this.product.employeeId +
              "/" +
              this.product.brandId +
              "/" +
              this.product.type_productId +
              "/" +
              this.product.quantity +
              "/" +
              this.product.price,
            this.product
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
      this.product.employeeId = null;
      this.product.brandId = null;
      this.product.type_productId = null;
      this.product.quantity = null;
      this.product.price = null;
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