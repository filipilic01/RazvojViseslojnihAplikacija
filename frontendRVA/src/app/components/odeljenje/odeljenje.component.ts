import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Odeljenje } from 'src/app/models/odeljenje';
import { OdeljenjeService } from 'src/app/services/odeljenje.service';
import { OdeljenjeDialogComponent } from '../dialogs/odeljenje-dialog/odeljenje-dialog.component';
import { ODELJENJE_URL } from 'src/app/app.constant';
import { PacijentService } from 'src/app/services/pacijent.service';



@Component({
  selector: 'app-odeljenje',
  templateUrl: './odeljenje.component.html',
  styleUrls: ['./odeljenje.component.css']
})
export class OdeljenjeComponent {
  subscription!: Subscription;
  displayedColumns= ['id', 'naziv', 'lokacija' , 'bolnica', 'actions'];
  dataSource!: MatTableDataSource<Odeljenje>;
  selektovanoOdeljenje1!: Odeljenje;//svaki put kad korisnik klikne na neki red setovace se ova varijabla
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private odeljenjeService: OdeljenjeService, private pacijentService: PacijentService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { 
    
      this.loadData(); 
  
  }


  public loadData() {
    this.subscription = this.odeljenjeService.getAllOdeljenje().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        //sortiramo po ugnjezdenom obelezju
        this.dataSource.sortingDataAccessor = (row: Odeljenje, columnName: string): string => {

          console.log(row, columnName);
          if (columnName == "bolnica") return row.bolnica.naziv.toLocaleLowerCase();
          var columnValue = row[columnName as keyof Odeljenje] as unknown as string;
          return columnValue;

        }

        this.dataSource.sort = this.sort;
        //filtriranje po ugnjezdenom obelezju
        this.dataSource.filterPredicate = (data, filter: string) => {
          const accumulator = (currentTerm: any, key: string) => {
            return key === 'bolnica' ? currentTerm + data.bolnica.naziv : currentTerm + data[key as keyof Odeljenje];
          };
          const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
          const transformedFilter = filter.trim().toLowerCase();
          return dataStr.indexOf(transformedFilter) !== -1;
        };

        this.dataSource.paginator = this.paginator;
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, odeljenje?: Odeljenje): void {
    const dialogRef = this.dialog.open(OdeljenjeDialogComponent, { data: (odeljenje ? odeljenje : new Odeljenje()) });
    //otvara modalni dijalog odgovarajuće komponente
    //vracamo instancu keirane komponente dialoga
    dialogRef.componentInstance.flagOdelDialog = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    })
  }

  selectRow(row: any) {
    this.selektovanoOdeljenje1 = row;
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}
