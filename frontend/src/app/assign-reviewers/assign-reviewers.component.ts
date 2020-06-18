import { Component, OnInit } from '@angular/core';
import { XmlParser } from '@angular/compiler';

@Component({
  selector: 'app-assign-reviewers',
  templateUrl: './assign-reviewers.component.html',
  styleUrls: ['./assign-reviewers.component.css']
})
export class AssignReviewersComponent implements OnInit {

  checkbox1: boolean = false;
  checkbox2: boolean = false;
  checkbox3: boolean = false;

  private elements : string = "";

  constructor() { }

  ngOnInit() {

  }

  makeReviewElement(){

    let reviewElement = "";

    /* Ukoliko je checkbox1 == true, na this.elements dodaje novi review_element, odgovarajuci checkboxu1 */
    if(this.checkbox1 == true)
    {
      console.log("Ovo je ako je checkbox1 = true")
      
      reviewElement = "<review_element>" +
                          "<review_id>" +
                          "</review_id>" +
                          "<reviewer_id>" +
                          "</reviewer_id>" +
                     "</review_element>"


      this.elements += reviewElement;

    }

    /* Ukoliko je checkbox2 == true, na this.elements dodaje novi review_element, odgovarajuci checkboxu2 */
    if(this.checkbox2 == true)
    {
      console.log("Ovo je ako je checkbox2 = true")

      reviewElement = "<review_element>" +
                          "<review_id>" +
                          "</review_id>" +
                          "<reviewer_id>" +
                          "</reviewer_id>" +
                     "</review_element>"

      this.elements += reviewElement;
    }

    /* Ukoliko je checkbox3 == true, na this.elements dodaje novi review_element, odgovarajuci checkboxu3 */
    if(this.checkbox3 == true)
    {
      console.log("Ovo je ako je checkbox3 = true")

      reviewElement = "<review_element>" +
                          "<review_id>" +
                          "</review_id>" +
                          "<reviewer_id>" +
                          "</reviewer_id>" +
                     "</review_element>"
      
      this.elements += reviewElement;

    }
  }

  click(ev){
    console.log(ev.target.defaultValue);
    this.elements //da bude lista a ne string i da se u tu listu doda defaultValue ukoliko je kliknut, a ukoliko nije - da se izbrise iz liste ukoliko postoji
  }

  assign(){
    this.makeReviewElement()

    console.log(this.elements) //prodji kroz sve this.elements i napravi formu XML za njih gde je reviewerID je jednak eleme
  }

}
