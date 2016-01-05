class { '::mysql::server':
  root_password           => 'root',
  override_options => { 'mysqld' => { 'max_connections' => '1024', 'bind-address' => '0.0.0.0' } },
  restart          => true,
}

mysql::db { 'REST':
  user     => 'rest',
  password => 'rest',
  host     => '%',
  sql      => '/vagrant/sql/rest.sql',
}