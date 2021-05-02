<template>
  <div class="submit-form">
    <div v-if="!submitted">
      <div class="form-group">
        <label for="title">Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="contents.title"
          name="title"
        />
      </div>

      <div class="form-group">
        <label for="description">Description</label>
        <input
          class="form-control"
          id="description"
          required
          v-model="contents.description"
          name="description"
        />
      </div>

      <button @click="saveContents" class="btn btn-success">Submit</button>
    </div>

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newContents">Add</button>
    </div>
  </div>
</template>

<script>
import ContentsDataService from "../services/ContentsDataService";

export default {
  name: "add-contents",
  data() {
    return {
      contents: {
        id: null,
        title: "",
        description: "",
        published: false
      },
      submitted: false
    };
  },
  methods: {
    saveContents() {
      var data = {
        title: this.contents.title,
        description: this.contents.description
      };

      ContentsDataService.create(data)
        .then(response => {
          this.contents.id = response.data.id;
          console.log(response.data);
          this.submitted = true;
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    newContents() {
      this.submitted = false;
      this.contents = {};
    }
  }
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>