<template>
<v-container>

    <v-row>
      <v-col cols="12" sm="4">
        <p>Employee</p>

        <v-overflow-btn
          class="my-2"
          :items="employees"
          label="Employee"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-overflow-btn>
      </v-col>
    </v-row>


 <v-row>
      <v-col cols="12" sm="4">
        <p>Member</p>

          <v-text-field
            v-model="message"
            solo
            label="Register"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
        </v-col>
    </v-row>

<v-row>
      <v-col cols="12" sm="4">
        <p>Products</p>

        <v-overflow-btn
          class="my-2"
          :items="product"
          label="Product"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-overflow-btn>
      </v-col>

      <v-col cols="12" sm="4">
        <p>Count Products</p>

          <v-text-field
            v-model="message2"
            solo
            label="ProductQty"
            :rules="[(v) => !!v || 'Item is required']"
            required
          ></v-text-field>
        </v-col>
    </v-row>

 <v-row>
      <v-col cols="12" sm="4">
        <p>PaymentType</p>

        <v-overflow-btn
          class="my-2"
          :items="paymenttypes"
          label="PaymentType"
          item-text="name"
          item-value="id"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-overflow-btn>
      </v-col>
 </v-row>
 <div class="my-2">
            <v-btn @click="savePayment" :class="{ red: !valid, green: valid }">Enter</v-btn>
        </div>

  </v-container>
</template>

