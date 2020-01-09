<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <v-col cols="12" sm="4">
        <p>Employee</p>

        <v-overflow-btn
          class="my-2"
          v-model="collectPoint.employeeId"
          :items="employees"
          label="Employee"
          item-text="username"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-overflow-btn>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="12" sm="4">
        <p>Register</p>

        <v-overflow-btn
          class="my-2"
          v-model="collectPoint.registerId"
          :items="registers"
          label="Register"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          @change="getPaymentcus"
        ></v-overflow-btn>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Payment</p>

        <v-overflow-btn
          class="my-2"
          v-model="collectPoint.paymentId"
          :items="paymentcus"
          label="Payment"
          item-text="id"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          @change="findPayment"
        ></v-overflow-btn>
        <p>Price : {{Cost}}</p>
      </v-col>
    </v-row>

    <v-row justify="center">
      <v-col cols="12" sm="4">
        <p>Point/Price</p>

        <v-overflow-btn
          class="my-2"
          v-model="collectPoint.pointPriceId"
          :items="pointprices"
          label="Price/Point"
          item-text="type"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
          @change="calPoint"
        ></v-overflow-btn>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Point</p>

          <v-text-field
            v-model="collectPoint.tpoint"
            solo
            label="Point"
            :rules="[(v) => !!v || 'Item is required']"
            disabled
            required
          ></v-text-field>
        </v-col>
    </v-row >

    <v-row justify="center">
         <v-col cols="12" sm="4">
            <v-btn @click="saveCollectPoint" color="green" dark>Collect
              <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>
            </v-col>
        <v-col cols="12" sm="4">
             <v-btn @click="clear" color="red" dark>Decline
        <v-icon dark right>mdi-cancel</v-icon>
      </v-btn>
        </v-col>
    </v-row >
     </v-card>
  </v-container>
</template>

<script>

import http from "../Api";

export default {
  name: "collectPoint",
  data() {
    return {
      
        collectPoint: {
        employeeId: "",
        registerId: "",
        paymentId: "",
        pointPriceId: "",
        tpoint: ""

      },

      valid: false,
      Cost: "",
      employees:[],
      registers:[],
      payments:[],
      paymentcus:[],
      collectPoints:[],
      pointprices:[]

    };

  },

  methods: {

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

    getRegisters() {
      http
        .get("/register")
        .then(response => {
          this.registers = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

    },

    getPayments() {
      http
        .get("/payment")
        .then(response => {
          this.payments = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

    },

    getPaymentcus() {
          this.paymentcus = ["ไม่มีข้อมูล"];
          let y = 0;
          this.Cost = null ;
         for (let j in this.payments) {
         if (this.payments[j].register.id == this.collectPoint.registerId) {   
          this.paymentcus[y] = this.payments[j];
          y++; 
          }
        }
    },

    getPointPrices() {
      http
        .get("/pointPrice")
        .then(response => {
          this.pointprices = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });

    },

    findPayment() {
            this.collectPoint.pointPriceId = "";
            this.collectPoint.tpoint = "";
            this.Cost = "" ;
            if(this.paymentcus != "ไม่มีข้อมูล"){
            this.Cost = this.payments[this.collectPoint.paymentId-1].cost;
            }
    },

    calPoint(){
        this.collectPoint.tpoint = "";
        let pr = this.payments[this.collectPoint.paymentId-1].cost;
        let p = this.pointprices[this.collectPoint.pointPriceId-1].type;
        this.collectPoint.tpoint = parseInt(pr / p);
    },

    saveCollectPoint() {
      http
        .post(
          "/collectPoint/" +
            this.collectPoint.employeeId +
            "/" +
            this.collectPoint.registerId +
            "/" +
            this.collectPoint.pointPriceId +
            "/" +
            this.collectPoint.paymentId +
            "/" +
            this.collectPoint.tpoint,
          this.collectPoint
        )
        .then(response => {
          console.log(response);
          alert("Collect Success");
          this.clear();
        })
        .catch(e => {
          console.log(e);
          this.submitted = false;
        });
      this.submitted = true;
    },
    clear() {
      this.collectPoint.registerId = "";
      this.collectPoint.paymentId = "";
      this.collectPoint.pointPriceId = "";
      this.collectPoint.tpoint = "";
      this.Cost = "";

    },
    refreshList() {
      this.getEmployees();
      this.getRegisters();
      this.getPayments();
      this.getPointPrices();
      this.getPaymentcus();
      //this.getCollectpoints();
    }

    /* eslint-enable no-console */
  },

  mounted() {
      this.getEmployees();
      this.getRegisters();
      this.getPayments();
      this.getPointPrices();
      this.getPaymentcus();
      //this.getCollectpoints();
  }

};

</script>