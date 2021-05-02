import http from "../http-common";

class ContentsDataService{

	getAll() {
		return	http.get("/contents");
	}

	get(id) {
		return	http.get(`/contents/${id}`);
	}

	create(data) {
		return	http.post("/contents", data);
	}

	update(id, data) {
		return	http.put(`/contents/${id}`, data);
	}

	delete(id) {
		return	http.delete(`/contents/${id}`);
	}

	deleteAll() {
		return	http.delete(`/contents`);
	}
	
	findByTitle(title) {
		return	http.get(`/contents/${title}`);
	}
}

export default new ContentsDataService();