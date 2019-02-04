import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-start-test',
  templateUrl: './start-test.component.html',
  styleUrls: ['./start-test.component.css']
})
export class StartTestComponent implements OnInit {
  testId;
  strtTime;
  endTime;
  constructor() { }

  ngOnInit() {
  }
  OnSubmit(form:NgForm){

  }

}
