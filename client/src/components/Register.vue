<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <v-col cols="10" sm="4">
        <v-select
          v-model="register.employeeId"
          :items="employees"
          :readonly="true"
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
          v-model="register.titleId"
          :items="titlenames"
          label="Title Name"
          item-text="title"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        outlined
        ></v-select>
      </v-col>

      <v-col cols="3" sm="4">
          <v-text-field
            label="Name"
            v-model="register.name"
            :rules="[(v) => !!v || 'Item is required']"
            required
            outlined
          ></v-text-field>
      </v-col>

      <v-col cols="3" sm="4">
        <v-text-field
            label="Last Name"
            v-model="register.lname"
            :rules="[(v) => !!v || 'Item is required']"
            required
            outlined
          ></v-text-field>
      </v-col>
    </v-row>
    
    <v-row justify="center">
      <v-col cols="3" sm="2">
        <v-select
          v-model="register.genderId"
          :items="genders"
          label="Gender"
          item-text="gender"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        outlined
        ></v-select>
      </v-col>

      <v-col cols="12" sm="12" md="8">
          <v-text-field
            label="Address"
            v-model="register.address"
            :rules="[(v) => !!v || 'Item is required']"
            required
            outlined
          ></v-text-field>
      </v-col>
      </v-row>

      <v-row justify="center">
      <v-col cols="12" sm="10" md="4">
          <v-text-field
            label="Tel"
            v-model="register.tel"
            :rules="[(v) => !!v || 'Item is required']"
            required
            outlined
          ></v-text-field>
      </v-col>
    </v-row>
  </v-card>

    <v-row justify="center">
      <v-btn rounded style="margin: 10px ; background-color: #00C853" @click="saveRegister" dark>REGISTER
        <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #E53935" @click="clear" dark>CLEAR
        <v-icon dark right>mdi-cancel</v-icon>
      </v-btn>
      <v-btn rounded style="margin: 10px ; background-color: #000000" @click="Logout" dark>LOG OUT
        <v-icon dark right>mdi-logout</v-icon>
      </v-btn>
    </v-row>
  </v-container>
</template>

<script>
import http from "../Api";

export default {
  name: "register",
  data() {
    return {
      register: {
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
      valid: false
    };
  },


  methods: {
    lockemployee(){
      this.emid = this.$route.params.em;
      this.register.employeeId  = this.emid;
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
    saveRegister() {
      if (
        !this.register.employeeId ||
        !this.register.titleId ||
        !this.register.name ||
        !this.register.lname ||
        !this.register.genderId ||
        !this.register.address ||
        !this.register.tel
      ) {
        alert("กรุณากรอกข้อมูลให้ครบถ้วน!");
      } else {
        http
          .post(
          "/register/"+
            this.register.employeeId +
            "/" +
            this.register.titleId +
            "/" +
            this.register.name +
            "/" +
            this.register.lname +
            "/" +
            this.register.genderId +
            "/" +
            this.register.address +
            "/" +
            this.register.tel,
          this.register
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
    
    clear() {
      //this.$refs.form.reset();
      this.register.genderId = ""
      this.register.titleId = ""
      this.register.name = ""
      this.register.lname = ""
      this.register.tel = ""
      this.register.address = ""
    },
  },
  mounted() {
    this.getEmployees();
    this.getTitleNames();
    this.getGenders();
    this.lockemployee();
  }
};
</script>
