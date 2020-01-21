<template>
  <v-container>
    <v-card color="#BCFFE9">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="text mt-5 pt-5"
      style="text-align: center;
      font: 40px Times New Roman, sans-serif;
      width: 100%;"><strong>SEARCH SALE RECORD</strong></h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
        <v-col cols="4">
            <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="insert username"
                v-model="searchSale.username"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-dialog v-model="dialog" persistent max-width="290" >
                  <template v-slot:activator="{ on }">
                    <v-btn v-on="on" @click="getEmployees" depressed large color="primary">Search</v-btn>
                  </template>
                <v-card>
                  <v-card-title class="headline">notification</v-card-title>
                    <v-card-text v-if="er" >This Employee Doesn't Has Sale Record</v-card-text>
                    <v-card-text v-if="!er" >Please Insert Username !!</v-card-text>
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
    </v-card>
  </v-container>
</template>

<script>
import http from "../Api";

export default {
  name: "searchSale",
  data() {
    return {
      searchSale: {
        username:""
      },
      headers: [
        { text: "DATE", value: "date" },
        { text: "EMPLOYEE", value: "employee.username" },
        { text: "CUSTOMER", value: "member.name" },
        { text: "PRICE", value: "price" },
      ],
      er:null,
      dialog:false,
      items:[]
    };
  },

  methods: {
    /* eslint-disable no-console */

    getEmployees() {
        if(this.searchSale.username == ""){
            http
            .get("/EmployeeUser/" + this.searchSale.username)
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
      this.searchSale.username = null;

    },
    /* eslint-disable no-console */
  },
  mounted() {
    this.getEmployees();
  }

};
</script>