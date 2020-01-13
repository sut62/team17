
<template>
  <v-container>
    
      <v-card color="teal lighten-5">
        <v-col></v-col>
          <h1 class="text mt-5 pt-5"
            style="text-align: center;
            font: 40px BankGothic Md BT, sans-serif;
            width: 100%;"><strong>PRE-ORDER</strong></h1>
          <v-col></v-col>

        <v-row justify="center">
      <v-col cols="2" sm="5">
        <v-select
          :readonly="true"
          v-model="Pre_Order.employeeId"
          :items="employees"
          label="Employee"
          item-text="username"
          item-value="id"
          outlined
        ></v-select>
        </v-col>
        </v-row>

    <v-row justify="center">
       <v-col class="" cols="6" sm="3">
        <v-select
          :items="titlenames"
          item-text="title"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.titleID"
          label="Title Name"
          outlined
        ></v-select>
        </v-col>

        <v-col cols="12" sm="6">
          <v-text-field
            :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
            required
            v-model="Pre_Order.cus_name"
            label="Name"
            outlined
          ></v-text-field>
          </v-col>
    </v-row>
  <v-row justify="center">
    <v-col class="" cols="6" sm="4">
          <v-text-field
            :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
            required
            v-model="Pre_Order.tel"
            label="Tel"
            outlined
          ></v-text-field>
          </v-col>
  </v-row>

  <v-row justify="center">
      <v-col cols="2" sm="3">
        <v-select
          :items="brands"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.brandID"
          label="Brand"
          outlined
        ></v-select>
        </v-col>

        <v-col cols="2" sm="3">
        <v-select
          :items="type_products"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.type_productID"
          label="Type Product"
          outlined
        ></v-select>
        </v-col>

        <v-col cols="2" sm="3">
        <v-text-field
            :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
            required
            v-model="Pre_Order.quantity"
            label="Quantity"
            outlined
          ></v-text-field>
        </v-col>
  </v-row>
    <v-col></v-col>

    <v-row justify="center">
        
         <div class="text-center">
            <v-btn rounded style="margin: 10px; background-color: #00C853" @click = "savePre_Order" dark>CONFIRM
              <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>

            <v-btn rounded style="margin: 10px; background-color: #E53935" @click = "Clear" dark>clear
              <v-icon dark right>mdi-cancel</v-icon>
            </v-btn>
            
            <v-btn rounded style="margin: 10px ; background-color: #000000" @click="Logout" dark>LOG OUT
              <v-icon dark right>mdi-logout</v-icon>
            </v-btn>
         </div>
        
  </v-row>

  <v-col></v-col>
 </v-card>
  </v-container>

</template>

<script>
import http from "../Api";

export default {
  name: "Pre_Order",
  data() {
    return {
      Pre_Order: {
        employeeId: "",
        titleID: "",
        cus_name: "",
        tel: "",
        brandID: "",
        type_productID: "",
        quantity: ""
      },
        employees: [],
        titlenames: [],
        brands: [],
        type_products: [],
        valid : false,
        emid: -99,
        lock:false
    };
  },
  methods: {
    lockemployee(){
      this.emid = this.$route.params.em;
      this.Pre_Order.employeeId  = this.emid;
      this.lock = true;
    },
    Logout(){
      this.$router.push("/")
    },
    /* eslint-disable no-console */
    
    getEmployees() {
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

    getTitleNames() {
      http
        .get("/titlename")
        .then(response => {
          this.titlenames = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getBrands() {
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
    
    getType_Products() {
      http
        .get("/type_product")
        .then(response => {
          this.type_products = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // function เมื่อกดปุ่ม ยืนยัน
    savePre_Order(){
      if (
        !this.Pre_Order.titleID ||
        !this.Pre_Order.cus_name ||
        !this.Pre_Order.tel ||
        !this.Pre_Order.brandID ||
        !this.Pre_Order.type_productID ||
        !this.Pre_Order.quantity
      ) {
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      }
        else {
      http
        .post(
          "/pre_order/" +
            this.Pre_Order.employeeId +
            "/" +
            this.Pre_Order.titleID +
            "/" +
            this.Pre_Order.cus_name +
            "/" +
            this.Pre_Order.tel +
            "/" +
            this.Pre_Order.brandID +
            "/" +
            this.Pre_Order.type_productID +
            "/" +
            this.Pre_Order.quantity,
          this.Pre_Order
        )
        .then(response => {
          alert("บันทึกข้อมูลสำเร็จ!");
          console.log(response);
          this.Clear();
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    }
    },
    Clear(){
            this.Pre_Order.titleID = null;
            this.Pre_Order.cus_name = null;
            this.Pre_Order.tel = null;
            this.Pre_Order.brandID = null;
            this.Pre_Order.type_productID = null;
            this.Pre_Order.quantity = null;
    },
    
    refreshList() {
    this.getEmployees();
    this.getTitleNames();
    this.getBrands();
    this.getType_Products();
    this.lockemployee();
    }
   
   /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getTitleNames();
    this.getBrands();
    this.getType_Products();
    this.lockemployee();
  }
}
</script>


