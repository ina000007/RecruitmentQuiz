import { College } from './../services/college/college.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-college',
  templateUrl: './add-college.component.html',
  styleUrls: ['./add-college.component.css']
})
export class AddCollegeComponent implements OnInit {
college : College;
  constructor() { }

  ngOnInit() {
    this.college = new College();
  }

}
