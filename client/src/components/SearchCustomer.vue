<template>
  <v-container>
    <v-card color="#BCFFE9">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="text mt-5 pt-5"
      style="text-align: center;
      font: 40px Times New Roman, sans-serif;
      width: 100%;"><strong>SEARCH CUSTOMER</strong></h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
        <v-col cols="4">
            <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="CUSTOMER TEL"
                v-model="searchCustomer.tel"
                :rules="[(v) => !!v || 'กรุณาใส่เบอร์โทรศัพท์ลูกค้า']"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-dialog v-model="dialog" persistent max-width="290" >
                  <template v-slot:activator="{ on }">
                    <v-btn v-on="on" @click="getCustomers" depressed large color="primary">Search</v-btn>
                  </template>
                <v-card>
                  <v-card-title class="headline">notification</v-card-title>
                    <v-card-text v-if="er" >This Customer isn't Register</v-card-text>
                    <v-card-text v-if="!er" >Please Insert Tel !!</v-card-text>
                      <v-card-actions><v-spacer></v-spacer>
                        <v-btn color="green darken-1" text @click="dialog = false">OK</v-btn>
                      </v-card-actions>
                </v-card>
                </v-dialog>
              </div>
            </v-col>
          </v-row>
        </v-col>
      </v-row>
    
    <v-row justify="center">
      <v-col  cols="8">
        <v-data-table :headers="headers" 
                      :items="items" 
                      :items-per-page="10" 
                      class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
    <v-row justify="center">
        <v-btn rounded style="margin: 10px ; background-color:#334b48" @click="Back" dark>BACK
        <v-icon dark left>mdi-arrow-left</v-icon>
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
  name: "searchCustomer",
  data() {
    return {
      searchCustomer: {
        tel:""
      },
      headers: [
        { text: "TITLE NAME", value: "titlename.title" },
        { text: "NAME", value: "name" },
        { text: "LAST NAME", value: "lname" },
        { text: "GENDER", value: "gender.gender" },
        { text: "ADDRESS", value: "address" },
        { text: "CREATED BY", value: "employee.username" },
        { text: "DATE CREATE", value: "date" }
      ],
      er:null,
      dialog:false,
      items:[]
    };
  },

  methods: {
    Back(){
      this.$router.push("/Customer")
    },
    Logout(){
      this.$router.push("/")
    },
    /* eslint-disable no-console */

    getCustomers() {
        if(this.searchCustomer.tel != ""){
            http
            .get("/customer/" + this.searchCustomer.tel)
            .then(response => {
            if(response.data != ""){
                this.items = response.data;
                console.log(this.items);
                this.dialog = false;
            }else{
                this.er = true;
                this.clear();
            }
            })
            .catch(e => {
                console.log(e);
            });
        }else{
            this.er = false;
        }
    },

    clear() {
      this.items = [];
      this.searchCustomer.tel = null;

    },
    /* eslint-disable no-console */
  },
  mounted() {
    this.getCustomers();
  }

};
</script>