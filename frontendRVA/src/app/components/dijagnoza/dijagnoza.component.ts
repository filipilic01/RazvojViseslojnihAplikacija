import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Dijagnoza } from 'src/app/models/dijagnoza';
import { DijagnozaService } from 'src/app/services/dijagnoza.service';
import { DijagnozaDialogComponent } from '../dialogs/dijagnoza-dialog/dijagnoza-dialog.component';

@Component({
  selector: 'app-dijagnoza',
  templateUrl: './dijagnoza.component.html',
  styleUrls: ['./dijagnoza.component.css']
})
export class DijagnozaComponent {

  constructor (private dijagnozaService: DijagnozaService, private dialog: MatDialog){}

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'opis','oznaka', 'actions'];
  dataSourceDijagnoza!: MatTableDataSource<Dijagnoza>;
  @ViewChild(MatSort, {static: false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;

  ngOnInit(){
    this.loadData();
  }

  loadData(){
    this.subscription=this.dijagnozaService.getAllDijagnoza().subscribe(
      data => {
        this.dataSourceDijagnoza = new MatTableDataSource(data);
        this.dataSourceDijagnoza.sort=this.sort;
        this.dataSourceDijagnoza.paginator=this.paginator;
      },
      error => {
        console.log(error.name + ' ' + error.message);
      
      }
      );
  }

  public openDialog(flag: number, dijagnoza?: Dijagnoza): void {
    const dialogRef = this.dialog.open(DijagnozaDialogComponent, {data: (dijagnoza?dijagnoza: new Dijagnoza())});
    dialogRef.componentInstance.flagDijagDialog= flag;
    dialogRef.afterClosed().subscribe(res=> {if(res===1) this.loadData();})
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSourceDijagnoza.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }

  ngOnDestroy(): void {this.subscription.unsubscribe();}
  ngOnChanges(){this.loadData();}
}
