<template>
  <v-container>
    <v-card color="teal lighten-5">
    <v-row justify="center">
      <h1 class="display-1 font-italic" style="margin: 15px ; color: rgb(205, 123, 147) ;"
      >COLLECT POINT</h1>
    </v-row>
    <v-row justify="center">
      
      <v-col cols="12" sm="4">
        <p>Employee</p>
        <v-overflow-btn
          :readonly="true"
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
        <p>Customer</p>

        <v-overflow-btn
          class="my-2"
          v-model="collectPoint.customerId"
          :items="customers"
          label="Customer"
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
         <v-col cols="10" sm="2">
            <v-btn @click="saveCollectPoint" color="green" dark>Collect
              <v-icon dark right>mdi-checkbox-marked-circle</v-icon>
            </v-btn>
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
        customerId: "",
        paymentId: "",
        pointPriceId: "",
        tpoint: ""

      },

      valid: false,
      Cost: "",
      employees:[],
      customers:[],
      payments:[],
      paymentcus:[],
      collectPoints:[],
      pointprices:[],
      emid: -99,
      lock:false

    };

  },

  methods: {

    /* eslint-disable no-console */
     lockemployee(){
      this.emid = this.$route.params.em;
      this.collectPoint.employeeId  = this.emid;
      this.lock = true;
    },
    Logout(){
      this.$router.push("/")
    },
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

    getCustomers() {
      http
        .get("/customer")
        .then(response => {
          this.customers = response.data;
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
          this.collectPoint.pointPriceId = "";
          this.collectPoint.tpoint = "";
          this.paymentcus = [];
          let l = 0;
          this.Cost = null ;
        if(this.collectPoint.customerId != null){        
         for (let j in this.payments) {           
          if (this.payments[j].member.id == this.collectPoint.customerId ){ 
            this.paymentcus[l] = this.payments[j];
            l++; 
          }
         } 
         for (let i in this.paymentcus) {
          if( this.paymentcus[i] != null){
            http
              .get("/collectPoint/" + this.paymentcus[i].id)
              .then(response => {
                if(response.data != ""){
                  this.paymentcus[i].id = "ทำรายการแล้ว";
                 }
        let pay = [];
        let k=0;
        for(let n in this.paymentcus){
          if(this.paymentcus[n].id != "ทำรายการแล้ว"){
            pay[k] = this.paymentcus[n];
            k++;
          }
        }
        this.paymentcus = pay;
              })
            .catch(e => {
              console.log(e);
            });
         }
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
            this.Cost =  "ไม่มีข้อมูล" ;
            this.Cost = this.payments[this.collectPoint.paymentId-1].price;
    },

    calPoint(){
        this.collectPoint.tpoint = "ไม่มีข้อมูล";
        let pr = this.payments[this.collectPoint.paymentId-1].price;
        let p = this.pointprices[this.collectPoint.pointPriceId-1].type;
        this.collectPoint.tpoint = parseInt(pr / p);
    },

    saveCollectPoint() {
      if(this.collectPoint.employeeId == ""||
      this.collectPoint.customerId == ""||
      this.collectPoint.paymentId == ""||
      this.collectPoint.pointPriceId == ""||
      this.collectPoint.tpoint == ""||
      this.collectPoint.paymentId == "ไม่มีข้อมูล"||
      this.collectPoint.tpoint == "ไม่มีข้อมูล"){
        alert("Please Select All !!");
        this.clear();}
      else{
      http
        .post(
          "/collectPoint/" +
            this.collectPoint.employeeId +
            "/" +
            this.collectPoint.customerId +
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

        });
      }
        
      this.submitted = true;
    },
    clear() {
      this.paymentcus = [];
      this.collectPoint.customerId = "";
      this.collectPoint.paymentId = "";
      this.collectPoint.pointPriceId = "";
      this.collectPoint.tpoint = "";
      this.Cost = "";

    },
    refreshList() {
      this.getEmployees();
      this.getCustomers();
      this.getPayments();
      this.getPointPrices();
      this.getPaymentcus();
      this.lockemployee();
    }

    /* eslint-enable no-console */
  },

  mounted() {
      this.getEmployees();
      this.getCustomers();
      this.getPayments();
      this.getPointPrices();
      this.getPaymentcus();
      this.lockemployee();
  }

};

</script>