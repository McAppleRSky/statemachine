CREATE TABLE s_assignment (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  spec_id BIGINT NOT NULL,
  wo_name VARCHAR(100),
  form_name VARCHAR(100),
  assoc_name VARCHAR(150) NOT NULL,
  assoc_spec_id BIGINT NOT NULL,
  assoc_wo_name VARCHAR(100),
  assoc_form_name VARCHAR(100),
  dependent_flag BOOLEAN,
  PRIMARY KEY (id) );
CREATE TABLE s_state (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  wo_name VARCHAR(100) NOT NULL,
  wo_state VARCHAR(100) NOT NULL,
  PRIMARY KEY (id) );
CREATE TABLE s_state_transit (
  id_state_transit BIGINT GENERATED BY DEFAULT AS IDENTITY,
  wo_name VARCHAR(100) NOT NULL,
  action_name VARCHAR(100) NOT NULL,
  action_label VARCHAR(100) NOT NULL,
  current_state VARCHAR(100) NOT NULL,
  next_state VARCHAR(100) NOT NULL,
  custom_url VARCHAR(500),
  class_name VARCHAR(1000),
  method_name VARCHAR(150),
  default_display BOOLEAN,
  read_only BOOLEAN,
  close_window BOOLEAN,
  secondary_action BOOLEAN,
  PRIMARY KEY (id_state_transit) );
CREATE TABLE s_statetransit_subaction (
  id_statetransit_subact BIGINT GENERATED BY DEFAULT AS IDENTITY,
  action_name VARCHAR(100) NOT NULL,
  id_state_transit BIGINT NOT NULL,
  process_name VARCHAR(150),
  PRIMARY KEY (id_statetransit_subact) );
CREATE TABLE s_statetransit_subact_attribute (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  field_name VARCHAR(100) NOT NULL,
  const_value VARCHAR(100) NOT NULL,
  id_state_transit BIGINT NOT NULL,
  id_statetransit_subact BIGINT NOT NULL, PRIMARY KEY (id) );
CREATE TABLE s_statetransit_subact_inclusion (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  action_name VARCHAR(100) NOT NULL,
  inclusion BOOLEAN,
  exclusion BOOLEAN,
  id_state_transit BIGINT NOT NULL,
  id_statetransit_subact BIGINT NOT NULL,
  PRIMARY KEY (id) );
