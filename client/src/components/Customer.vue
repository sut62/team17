<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <h1 class="text mt-5 pt-5"
      style="text-align: center;
      font: 40px BankGothic Md BT, sans-serif;
      width: 100%;"><strong>REGISTER CUSTOMER</strong></h1>
    </v-row>

    <v-row justify="center">
      <v-col cols="10" sm="4">
        <v-select
          :readonly="true"
          v-model="customer.employeeId"
          :items="employees"
          label="Employee"
          item-text="username"
          item-value="id"
          outlined
        ></v-select>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col id = "1" cols="3" sm="2">
        <v-select
          v-model="customer.titleId"
          :items="titlenames"
          label="Title Name"
          item-text="title"
          item-value="id"
          :rules="[(v) => !!v || '*กรุณาเลือกคำนำหน้าชื่อ']"
          required
        outlined
        ></v-select>
      </v-col>

      <v-col id = "2" cols="3" sm="4">
          <v-text-field
            label="Name"
            v-model="customer.name"
            :rules="[(v) => !!v || '*กรุณาใส่ชื่อจริง']"
            required
            outlined
          ></v-text-field>
      </v-col>

      <v-col id = "3" cols="3" sm="4">
        <v-text-field
            label="Last Name"
            v-model="customer.lname"
            :rules="[(v) => !!v || '*กรุณาใส่นามสกุล']"
            required
            outlined
          ></v-text-field>
      </v-col>
    </v-row>
    
    <v-row justify="center">
      <v-col id = "4" cols="3" sm="2">
        <v-select
          v-model="customer.genderId"
          :items="genders"
          label="Gender"
          item-text="gender"
          item-value="id"
          :rules="[(v) => !!v || '*กรุณาเลือกเพศ']"
          required
        outlined
        ></v-select>
      </v-col>

      <v-col id = "5" cols="12" sm="12" md="8">
          <v-text-field
            label="Address"
            v-model="customer.address"
            :rules="[(v) => !!v || '*กรุณาใส่ที่อยู่ปัจจุบัน']"
            required
            outlined
          ></v-text-field>
      </v-col>
      </v-row>

      <v-row justify="center">
      <v-col id = "6" cols="12" sm="10" md="4">
          <v-text-field
            label="Tel"
            v-model="customer.tel"
            :rules="[(v) => !!v || '*กรุณาใสเบอร์โทรศัพท์']"
            required
            outlined
          ></v-text-field>
      </v-col>
    </v-row>
    
    <v-row justify="center">
        <v-col cols="10" sm="2">
          <v-dialog v-model="dialog" persistent max-width="290" >
           <template v-slot:activator="{ on }">
            <v-btn v-on="on" @click="saveCustomer" color="green" dark>REGISTER
              <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>
           </template>
          <v-card>
            <v-card-title class="headline">notification</v-card-title>
              <v-card-text v-if="suc" >Register Success</v-card-text>
              <v-card-text v-if="!suc" >Please Select All !!</v-card-text>
                <v-card-actions><v-spacer></v-spacer>
                  <v-btn color="green darken-1" text @click="dialog = false">OK</v-btn>
                </v-card-actions>
          </v-card>
          </v-dialog>
        </v-col>
        <v-col cols="10" sm="2">
            <v-btn @click="clear" color="red" dark>Decline
              <v-icon dark right>mdi-cancel</v-icon>
            </v-btn>
        </v-col>
        <v-col cols="10" sm="2">
            <v-btn style="background-color: #000000" @click="Logout" dark>LOG OUT
              <v-icon dark right>mdi-logout</v-icon>
            </v-btn>
        </v-col>
    </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "../Api";

export default {
  name: "customer",
  data() {
    return {
      customer: {
        name: "",
        lname: "",
        address: "",
        tel: "",
        employeeId: "",
        titleId: "",
        genderId: ""
      },
      employees:[],
      titlenames:[],
      genders:[],
      valid: false,
      emid: -99,
      lock:false,
      dialog:false,
      suc:null
    };
  },


  methods: {
    lockemployee(){
      this.customer.employeeId  = JSON.parse(localStorage.getItem("id"));
      this.lock = true;
    },
    /*back(){
      this.$router.push({name: 'Dashboard' , params: {em: this.emid} }); 
    },*/
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

    getGenders() {
      http
        .get("/Gender")
        .then(response => {
          this.genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // function เมื่อกดปุ่มบันทึกข้อมูล
    saveCustomer() {
      if (
        !this.customer.name ||
        !this.customer.lname ||
        !this.customer.address ||
        !this.customer.tel ||
        !this.customer.titleId ||
        !this.customer.genderId
      ) {
        this.suc = false;
        this.clear();
      } else {
        http
          .post(
          "/customer/"+
            this.customer.name +
            "/" +
            this.customer.lname +
            "/" +
            this.customer.address +
            "/" +
            this.customer.tel +
            "/" +
            this.customer.employeeId +
            "/" +
            this.customer.titleId +
            "/" +
            this.customer.genderId,
          this.customer
       )
          .then(response => {
            console.log(response);
            this.suc = true;
            this.clear();
          })
          .catch(e => {
            console.log(e);
          });
    }
    },
    
    clear() {
      //this.$refs.form.reset();
      this.customer.genderId = null;
      this.customer.titleId = null;
      this.customer.name = null;
      this.customer.lname = null;
      this.customer.tel = null;
      this.customer.address = null;
    },
    refreshList() {
    this.getEmployees();
    this.getTitleNames();
    this.getGenders();
    this.lockemployee();
    }
   
   /* eslint-enable no-console */
  },
  mounted() {
    this.getEmployees();
    this.getTitleNames();
    this.getGenders();
    this.lockemployee();
  }
};
</script>
