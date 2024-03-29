var classes;
var video;
var capture;

function getFrame(video){
	capture = video.get();
	image(capture, 0, 0, 1280, 720);
	return capture
}

function setup() {
    createCanvas(1280, 720)
    background(51);
    video = createCapture(VIDEO);
    video.size(1280, 720);

    const button1 = document.getElementById('capture');
    button1.addEventListener('click', async event => {
      setTimeout(function(){capture = getFrame(video)},5000)
	});

	const button2 = document.getElementById('submit');
	button2.addEventListener('click', async event =>{
		
      //load video pixels onto the canvas
      capture.loadPixels();
      //toDataURL(): convert video canvas to base64
      const image_serialized = capture.canvas.toDataURL();

      classes = "['12','15','21','29','37','45','46']";
      const data = { classes, image_serialized };

      //added to deal with CORS policy block issues
      let headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('Accept', 'application/json');
      headers.append('Origin','http://localhost:8080');
      //headers.append('Access-Control-Allow-Origin','http://localhost:8000');

      const options = {
        mode: 'cors',
        method: 'POST',
        headers: headers,
        body: JSON.stringify(data)
      };
      const response = await fetch('http://localhost:8000/result', options);
      const json = await response.json();
      console.log(json);

	  //draw bounding box and write result with data from JSON
	  const json_object = JSON.parse(json);
	  const word_idx = json_object.word_index;
	  const box = json_object.box_coord;
	  const conf = json_object.confidence;
	  
	  textSize(32);
	  text(word_idx, 10, 30);
	  fill(0, 102, 153, 51);
	  text(conf, 10, 60);

	  nofill();
	  rect(box[0],box[1],box[2],box[3]);
	});  
}
