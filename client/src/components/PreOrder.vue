
<template>
  <v-container>
    

    <v-col></v-col>
    
      <v-card color="teal lighten-5">
        <v-row justify="center">
      <v-col cols="10">
            <v-btn @click="Back" color="#584656" style=" position: absolute; left: 20px;" dark>
              <v-icon dark >mdi-arrow-left-bold</v-icon>
                Back
            </v-btn>
        </v-col> 
        
      <h1 class="display-2" style="position: absolute; top: 20px; color: #3b4255 ;"
      ><strong>PRE-ORDER</strong></h1>

      <v-col cols="10" sm="2">
            <v-btn  @click="Logout" color ="#C53F52" style=" left: 45px;" dark>LOG OUT
              <v-icon dark right>mdi-logout</v-icon>
            </v-btn>
        </v-col>
    </v-row>
    <v-col></v-col>

        <v-row justify="center">
      <v-col cols="10" sm="4">
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
       <v-col id ="1" class="" cols="6" sm="3">
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
      <v-col id="2" cols="2" sm="3">
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

        <v-col id="3" cols="2" sm="3">
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

        <v-col id="4" cols="2" sm="3">
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
        <v-col col="10" sm="2">
          <v-dialog v-model="dialog" persistent max-width="290" >
           <template v-slot:activator="{ on }">
            <v-btn v-on="on" @click="savePre_Order" color="#408358" dark>Comfirm
              <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>
           </template>
          <v-card>
            <v-card-title class="headline">Notification</v-card-title>
              <v-card-text v-if="suc" >Success</v-card-text>
              <v-card-text v-if="!suc" >Please fill your information completely.</v-card-text>
                <v-card-actions><v-spacer></v-spacer>
                  <v-btn color="green darken-1" text @click="dialog = false">OK</v-btn>
                </v-card-actions>
          </v-card>
          </v-dialog>
        </v-col>

        <v-col col="10" sm="2">
            <v-btn @click="Clear" color="#c79799" dark>Clear
              <v-icon dark right>mdi-cancel</v-icon>
            </v-btn>
        </v-col>

        <v-col col="10" sm="2">
            <v-btn @click="Show" color="#ffb55a" dark>Show Pre-order list
            </v-btn>
        </v-col>
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
        lock:false,
        dialog:false,
        suc:null
    };
  },
  methods: {
    lockemployee(){
      this.Pre_Order.employeeId  = JSON.parse(localStorage.getItem("id"));
      this.lock = true;
    },
    Logout(){
      this.$router.push("/")
    },
    Back(){
      this.$router.push("/Dashboard")
    },
    Show(){
      this.$router.push("/ShowPreOrder")
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
        .get("/type_Product")
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
        this.suc = false;
        this.clear();
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
          console.log(response);
          this.suc = true;
          this.Clear();
        })
        .catch(e => {
          console.log(e);
        });
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


