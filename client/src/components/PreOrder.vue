
<template>
  <v-container>
    
      <v-card color="#E6BFDB">
        <v-col></v-col>
        <v-row justify="center">
      <v-col cols="2" sm="5">
        <v-select
          :items="employees"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.employeeID"
          label="Employee"
          outlined
        ></v-select>
        </v-col>
        </v-row>

    <v-row justify="center">
       <v-col class="" cols="6" sm="3">
        <v-select
          :items="titlenames"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.titlenameID"
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
        <v-select
          :items="quantitys"
          item-text="quantity"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.quantityID"
          label="Quantity"
          outlined
        ></v-select>
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
        employeeID: "",
        titlenameID: "",
        cus_name: "",
        tel: "",
        brandID: "",
        type_productID: "",
        quantityID: ""
      },
      valid : false,
        employees: [],
        titlenames: [],
        brands: [],
        type_products: [],
        quantitys: []
    };
  },
  methods: {
    /* eslint-disable no-console */

    getEmployees() {
      http
        .get("/employee")
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
    
    getQuantitys() {
      http
        .get("/quantity")
        .then(response => {
          this.quantitys = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // function เมื่อกดปุ่ม ยืนยัน
    savePre_Order(){
      if (
        !this.Pre_Order.employeeID ||
        !this.Pre_Order.titlenameID ||
        !this.Pre_Order.cus_name ||
        !this.Pre_Order.tel ||
        !this.Pre_Order.brandID ||
        !this.Pre_Order.type_productID ||
        !this.Pre_Order.quantityID
      ) {
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      }
        else {
      http
        .post(
          "/pre_order/" +
            this.Pre_Order.employeeID +
            "/" +
            this.Pre_Order.titlenameID +
            "/" +
            this.Pre_Order.cus_name +
            "/" +
            this.Pre_Order.tel +
            "/" +
            this.Pre_Order.brandID +
            "/" +
            this.Pre_Order.type_productID +
            "/" +
            this.Pre_Order.quantityID,
          this.Pre_Order
        )
        .then(response => {
          alert("บันทึกข้อมูลสำเร็จ!");
          console.log(response);
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    }
    },
    Clear(){
            this.Pre_Order.titlenameID = null;
            this.Pre_Order.cus_name = null;
            this.Pre_Order.tel = null;
            this.Pre_Order.brandID = null;
            this.Pre_Order.type_productID = null;
            this.Pre_Order.quantityID = null;
    },
    
    refreshList() {
    this.getEmployees();
    this.getTitleNames();
    this.getBrands();
    this.getType_Products();
    this.getQuantitys();
    }
   
   /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getTitleNames();
    this.getBrands();
    this.getType_Products();
    this.getQuantitys();
  }
}
</script>


