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
      <v-col cols="3" sm="2">
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

      <v-col cols="3" sm="4">
          <v-text-field
            label="Name"
            v-model="customer.name"
            :rules="[(v) => !!v || '*กรุณาใส่ชื่อจริง']"
            required
            outlined
          ></v-text-field>
      </v-col>

      <v-col cols="3" sm="4">
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
      <v-col cols="3" sm="2">
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

      <v-col cols="12" sm="12" md="8">
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
      <v-col cols="12" sm="10" md="4">
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
      <v-btn rounded style="margin: 10px ; background-color: #00C853" @click="saveCustomer" dark>REGISTER
        <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #E53935" @click="clear" dark>CLEAR
        <v-icon dark right>mdi-cancel</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #000000" @click="Logout" dark>LOG OUT
        <v-icon dark right>mdi-logout</v-icon>
      </v-btn>
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
      lock:false
    };
  },


  methods: {
    lockemployee(){
      this.emid = this.$route.params.em;
      this.customer.employeeId  = this.emid;
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
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
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
            alert("บันทึกข้อมูลสำเร็จ!");
            console.log(response);
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
