<template>
<v-container class="ml-auto mt-3 pt-3 mr-auto">
    <v-card justify="center">
    <h1 class="text mt-5 pt-5"><strong>Menu</strong></h1>
    <v-form>
        <p></p>
        <v-row justify="center">
            <v-btn @click="Customer" class="cyan lighten-1 white--text">Register Customer</v-btn>
        </v-row>
        <p></p>
        <v-row justify="center">
            <v-btn @click="Product" class="cyan lighten-1 white--text">Product</v-btn>
        </v-row>
        <p></p>
        <v-row justify="center">
            <v-btn @click="CollectPoint" class="cyan lighten-1 white--text">Collect Point</v-btn>
        </v-row>
        <p></p>
        <v-row justify="center">
            <v-btn @click="Pre_Order" class="cyan lighten-1 white--text">Pre Order</v-btn>
        </v-row>
        <p></p>
        <v-row justify="center">
            <v-btn @click="Logout" class="red white--text">Logout</v-btn>
        </v-row>
        <p></p>
       <v-col></v-col>
    </v-form>
    </v-card>
</v-container>
</template>

<script>
export default {
    methods: {
        Logout(){
            localStorage.setItem("id","");
            this.$router.push("/")
        },
        Customer(){
            this.$router.push({name: 'Customer' });
        },
        Product(){
            this.$router.push({name: 'Product'});
        },
        CollectPoint(){
            this.$router.push({name: 'CollectPoint' });
        },
        Pre_Order(){
            this.$router.push({name: 'PreOrder'})
        }
    },
}
</script>

<style scoped>
span {
  font: 20px BankGothic Md BT, sans-serif;
  -width: 100%;
}
h1.text {
  text-align: center;
  font: 30px BankGothic Md BT, sans-serif;
  width: 100%;
}
</style>