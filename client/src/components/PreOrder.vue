
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
          :items="title_names"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.title_nameID"
          label="Title"
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
          :items="numbers"
          item-text="num"
          item-value="id"
          :rules="[(v) => !!v || 'กรุณาใส่ข้อมูล']"
          required
          v-model="Pre_Order.numberID"
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
import http from "../http-common";

export default {
  name: "Pre_Order",
  data() {
    return {
      Pre_Order: {
        employeeID: "",
        title_nameID: "",
        cus_name: "",
        tel: "",
        brandID: "",
        type_productID: "",
        numberID: ""
      },
      valid : false,
        employees: [],
        title_names: [],
        brands: [],
        type_products: [],
        numbers: []
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

    getTitle_Names() {
      http
        .get("/title_name")
        .then(response => {
          this.title_names = response.data;
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
    
    getNumbers() {
      http
        .get("/number")
        .then(response => {
          this.numbers = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    // function เมื่อกดปุ่ม ยืนยัน
    savePre_Order() {
      http
        .post(
          "/pre_order/" +
            this.Pre_Order.employeeID +
            "/" +
            this.Pre_Order.title_nameID +
            "/" +
            this.Pre_Order.cus_name +
            "/" +
            this.Pre_Order.tel +
            "/" +
            this.Pre_Order.brandID +
            "/" +
            this.Pre_Order.type_productID +
            "/" +
            this.Pre_Order.numberID,
          this.Pre_Order
        )
        .then(response => {
          console.log(response);
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    Clear(){
            this.Pre_Order.title_nameID = null;
            this.Pre_Order.cus_name = null;
            this.Pre_Order.tel = null;
            this.Pre_Order.brandID = null;
            this.Pre_Order.type_productID = null;
            this.Pre_Order.numberID = null;
    },
    
    refreshList() {
    this.getEmployees();
    this.getTitle_Names();
    this.getBrands();
    this.getType_Products();
    this.getNumbers();
    }
   
   /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getTitle_Names();
    this.getBrands();
    this.getType_Products();
    this.getNumbers();
  }
}
</script>


