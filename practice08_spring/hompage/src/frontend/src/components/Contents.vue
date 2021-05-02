<template>
  <div v-if="currentContents" class="edit-form">
    <h4>Contents</h4>
    <form>
      <div class="form-group">
        <label for="title">Title</label>
        <input type="text" class="form-control" id="title"
          v-model="currentContents.title"
        />
      </div>
      <div class="form-group">
        <label for="description">Description</label>
        <input type="text" class="form-control" id="description"
          v-model="currentContents.contents"
        />
      </div>

      <!-- <div class="form-group">
        <label><strong>Status:</strong></label>
        {{ currentContents.published ? "Published" : "Pending" }}
      </div> -->
    </form>

    <!-- <button class="badge badge-primary mr-2"
      v-if="currentContents.published"
      @click="updatePublished(false)">
      UnPublish
    </button>
    <button v-else class="badge badge-primary mr-2"
      @click="updatePublished(true)">
      Publish
    </button> -->

    <button class="badge badge-danger mr-2"
      @click="deleteContents">
      Delete
    </button>

    <button type="submit" class="badge badge-success"
      @click="updateContents">
      Update
    </button>
    <p>{{ message }}</p>
  </div>

  <div v-else>
    <br />
    <p>Please click on a Contents...</p>
  </div>
</template>

<script>
import ContentsDataService from "../services/ContentsDataService";

export default {
  name: "contents",
  data() {
    return {
      currentContents: null,
      message: ''
    };
  },
  methods: {
    getContents(id) {
      ContentsDataService.get(id)
        .then(response => {
          this.currentContents = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    // updatePublished(status) {
    //   var data = {
    //     id: this.currentContents.id,
    //     title: this.currentContents.title,
    //     description: this.currentContents.description,
    //     published: status
    //   };

    //   ContentsDataService.update(this.currentContents.id, data)
    //     .then(response => {
    //       this.currentContents.published = status;
    //       console.log(response.data);
    //     })
    //     .catch(e => {
    //       console.log(e);
    //     });
    // },

    updateContents() {
      ContentsDataService.update(this.currentContents.id, this.currentContents)
        .then(response => {
          console.log(response.data);
          this.message = 'The contents was updated successfully!';
        })
        .catch(e => {
          console.log(e);
        });
    },

    deleteContents() {
      ContentsDataService.delete(this.currentContents.id)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: "contents" });
        })
        .catch(e => {
          console.log(e);
        });
    }
  },
  mounted() {
    this.message = '';
    this.getContents(this.$route.params.id);
  }
};
</script>

<style>
.edit-form {
  max-width: 300px;
  margin: auto;
}
</style>