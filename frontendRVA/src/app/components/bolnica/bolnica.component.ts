import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs/internal/Subscription';
import { Bolnica } from 'src/app/models/bolnica';
import { BolnicaService } from 'src/app/services/bolnica.service';

@Component({
  selector: 'app-bolnica',
  templateUrl: './bolnica.component.html',
  styleUrls: ['./bolnica.component.css']
})
export class BolnicaComponent {

  constructor (private bolnicaService: BolnicaService){}

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa','budzet', 'actions'];
  dataSource!: MatTableDataSource<Bolnica>;

  ngOnInit(){
    this.loadData();
  }

  loadData(){
    this.bolnicaService.getAllBolnica().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
      },
      error => {
        console.log(error.name + ' ' + error.message);
      
      }
      );
  }
}
